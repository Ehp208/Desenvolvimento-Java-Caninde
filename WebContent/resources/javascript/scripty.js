var arrayIdsElementsPage = new Array;
var idundefined = 'idundefined';
var classTypeString = 'java.lang.String';
var classTypeLong = 'java.lang.Long';
var classTypeDate = 'java.util.Date';
var classTypeBoolean = 'java.lang.Boolean';
var classTypeBigDecimal = 'java.math.BigDecimal';

function copiarValorFantasiaRazao(campo) {
	var idCampoDestino = getValorElementPorIdJQuery('razao');
	var textParaCopia = campo.value;
	
	var textCampoDestino = $(idCampoDestino).val();
	
	if (textCampoDestino === null || textCampoDestino === '' || textCampoDestino === ' '){
		$(idCampoDestino).val(textParaCopia);
	}	
}


function reloadPage() {
	$(function() {
		location.reload();
	});
	
}	

function validaDescricao(descricao) {
	if (descricao === ' ' || descricao.trim() === '') {
		return "Descrição não foi informada.";
	}
	 else {
		return descricao;
	}
}

// invalida a sessão do spring security
function logout(contextPath) {
	
	document.location =	 contextPath + '/j_spring_security_logout';
	var post = 'invalidar_session';
	$.ajax(
		{ 
		  type: "POST", 
		  url: post
		});
}

/**
 * Usada apenas para o menu do sistema Limpar variaveis por ajax e redireciona
 * sempre a pagina
 * 
 * @param context
 * @param pagina
 * @param post
 */
function redirecionarPage(context, pagina, post) { 
	pagina = pagina + post + ".jsf";
	$.ajax(
			{ type: "POST",
			  url: post
			}).always(function(resposta) { 
					document.location = context + pagina;
			});
}

function redirecionarPagina(context, pagina) { 
	pagina = pagina + ".jsf";
	document.location = context + pagina;
}

function invalidarSession(context, pagina) { 
     document.location = (context + pagina + ".jsf");
}

function permitNumber(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function naoPermiteEntradaDeDados(e) {
	return false;
}

function permitAlphaNumerico(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9) {
		return true;
	}
}

function permitDecimal(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9 && unicode != 46) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function addMascaraDecimalMonetaria(id) { 
	var id = getValorElementPorId(id);
	if (id != idundefined) {
		jQuery(function($){
			$("#"+id).maskMoney({precision:2, decimal:",", thousands:"."}); 
		});	
	}
}

function validarSenhaLogin() {
	j_username = document.getElementById('j_username').value;
	j_password = document.getElementById('j_password').value;

	if (j_username === null || j_username.trim() === '') {
		alert("Informe o Login.");
		 $("#j_username").focus();
		return false;
	}

	if (j_password === null || j_password.trim() === '') {
		alert("Informe a Senha.");
		 $("#j_password").focus();
		return false;
	}
	return true;
}

function initTamplete() {
	$(document).ready(function() {
	  $('#menupop').hide();
	  $('#barramenu').hide();
	  $('#barramenu').css("left", "-200px");
	  $('#iniciarmenu').click(function() {
	  	if ($('#barramenu').is(':visible')) {
	  	  ocultarMenu();
	  	} else {
	  	  $('#barramenu').show();
	  	  $('#barramenu').animate({"left":"0px"}, "slow");	
	  	}
	  });
	});
}

function ocultarMenu() {
	  $('#barramenu').animate({"left":"-200px"}, "slow", function() {
	  	$('#barramenu').hide();
	  });
}
	
function abrirMenupop() {
	  $('#menupop').show('slow').mouseleave(function() {
	  	fecharMenupop();
	  });
}
	
function fecharMenupop() {
	  if ($("#menupop").is(":visible")) {
	  	$('#menupop').hide('slow');
	  }
}

function fecharPesquisa() {
		$('#paginapesquisa').html('&nbsp;');
		$('#valorpesquisa').val('');
		$('#dialogopesquisa').hide();
}
	
function localeData_pt_br() {
		PrimeFaces.locales['pt'] = {
			closeText : 'Fechar',
			prevText : 'Anterior',
			nextText : 'Próximo',
			currentText : 'Começo',
			monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio',
					'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
					'Dezembro' ],
			monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
					'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
			dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
					'Sexta', 'Sábado' ],
			dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
			dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
			weekHeader : 'Semana',
			firstDay : 0,
			isRTL : false,
			showMonthAfterYear : false,
			yearSuffix : '',
			timeOnlyTitle : 'São Horas',
			timeText : 'Tempo',
			hourText : 'Hora',
			minuteText : 'Minuto',
			secondText : 'Segundo',
			ampm : false,
			month : 'Mês',
			week : 'Semana',
			day : 'Dia',
			allDayText : 'Todo o Dia'
		};
}

function validarCampoPesquisa(valor) {
	if ( valor != undefined  &&  valor.value != undefined ) {
		if (valor.value.trim() === '') {
			valor.value = '';
		}else {
			valor.value = valor.value.trim();
		}
	}
}

/**
 * Carrega um array global com os ids de todos os componentes da pagina Para ter
 * faciliades em obtencao de valores dos componentes bem como trabalhar com ajax
 */
function carregarIdElementosPagina() {
	 arrayIdsElementsPage = new Array;
	 for (form = 0 ; form <= document.forms.length; form++){
		 var formAtual = document.forms[form];
		 if (formAtual != undefined) {
			 for (i = 0; i< document.forms[form].elements.length; i++){
				 if(document.forms[form].elements[i].id != '') {
					 arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
				 }
			 }	
		 }
	 }
}
/**
 * Retorno o valor do id do componente dentro do documento html passando como
 * parametro a descrição do id declarada em jsf
 * 
 * @param id
 */
function getValorElementPorId(id) {
	 carregarIdElementosPagina();
	 for (i = 0; i< arrayIdsElementsPage.length; i++){
		 var valor =  ""+arrayIdsElementsPage[i];
		 if (valor.indexOf(id) > -1) {
			return valor;
	}
  }	
 return idundefined;
}

/**
 * primefaces.js código fonte
 * escapeClientId:function(a){return"#"+a.replace(/:/g,"\\:")}
 * 
 * @param id
 * @returns id
 */
function getValorElementPorIdJQuery(id) {
	var id = getValorElementPorId(id);
	if (id.trim() != idundefined) {
		 return PrimeFaces.escapeClientId(id);
	}
	
	 return idundefined;
}

/**
 * Adiciona foco ao campo passado como paramentro
 * 
 * @param campo
 */
function addFocoAoCampo(campo) {
	var id = getValorElementPorId(campo);
	if (id != idundefined) {
		document.getElementById(getValorElementPorId(id)).focus();
	}
}


/**
 * Gera automaticamente mascara para a tela de pesquisa 
 * 		var classTypeString = 'java.lang.String'; 
 * 		var classTypeLong = 'java.lang.Long'; 
 * 		var classTypeDate = 'java.util.Date'; 
 * 		var classTypeBoolean = 'java.lang.Boolean'; 
 * 		var classTypeBigDecimal = 'java.math.BigDecimal';
 * 
 * @param elemento
 */
function addMascaraPesquisa(elemento) {
	var vals = elemento.split("*");
	var campoBanco = vals[0];
	var typeCampo = vals[1];

	var idGeral = getValorElementPorIdJQuery('valorPesquisa'); 
	var idData = getValorElementPorIdJQuery('dataInicialPesquisa'); 
	var idValor = getValorElementPorIdJQuery('valorInicialPesquisa');
	
	var idItensCampoPesquisa = getValorElementPorIdJQuery('itensCampoPesquisa');	

	
	var idLabelCampo = getValorElementPorIdJQuery('labelCampo');	
	var idLabelCondicao = getValorElementPorIdJQuery('labelCondicao');
	var idCondicao = getValorElementPorIdJQuery('condicao');
	var idItensCondicao = getValorElementPorIdJQuery('itensCondicao');	
	
		
	if (idGeral != idundefined || idData != idundefined || idValor != idundefined) {
		jQuery(function($) {
			
			if (typeCampo == 'java.util.Date') {				
				$(idData).unmask(); 
 				$(idData).unbind('keypress'); 
				$(idData).unbind('keyup');
				$(idData).unbind('focus');
				$(idData).val('');
				$(idData).mask('99/99/9999');
				$(idData).show();
			
				idData = getValorElementPorIdJQuery('dataFinalPesquisa'); 
				$(idData).unmask(); 
				$(idData).unbind('keypress'); 
				$(idData).unbind('keyup');
				$(idData).unbind('focus');
				$(idData).val('');
				$(idData).mask('99/99/9999');
				$(idData).show();

				
				
				
				idItensCampoPesquisa = getValorElementPorIdJQuery('itensCampoPesquisa');	
				$(idItensCampoPesquisa).val('Intervalo Data');
				$(idItensCampoPesquisa).unbind('keypress');
				$(idItensCampoPesquisa).unbind('keyup');
				$(idItensCampoPesquisa).unbind('focus');
				
				
				idCondicao = getValorElementPorIdJQuery('labelCondicao');
				$(idCondicao).hide();
				
				idItensCondicao = getValorElementPorIdJQuery('itensCondicao');
				$(idItensCondicao).hide();
								
				idValor = getValorElementPorIdJQuery('valorInicialPesquisa');
				$(idValor).hide();
				
				idValor = getValorElementPorIdJQuery('valorFinalPesquisa'); 
				$(idValor).hide();

				idGeral = getValorElementPorIdJQuery('valorPesquisa'); 
				$(idGeral).hide();
				
				addFocoAoCampo('dataInicialPesquisa'); }			
			else if (typeCampo == 'java.math.BigDecimal') {				
				$(idValor).unmask();
				$(idValor).unbind('keypress'); 
				$(idValor).unbind('keyup');
				$(idValor).unbind('focus');
				$(idValor).val('');

				$(idValor).maskMoney( {precision:4, decimal:',', thousands:'.'} ); 
				$(idValor).show();
			
				idValor = getValorElementPorIdJQuery('valorFinalPesquisa'); 
				$(idValor).unbind('keypress'); 
				$(idValor).unbind('keyup');
				$(idValor).unbind('focus');
				$(idValor).val('');
				$(idValor).show();

				idCondicao = getValorElementPorIdJQuery('labelCondicao');
				$(idCondicao).hide();
				
				idItensCondicao = getValorElementPorIdJQuery('itensCondicao');
				$(idItensCondicao).hide();

				idData = getValorElementPorIdJQuery('dataInicialPesquisa');
				$(idData).hide();
				
				idData = getValorElementPorIdJQuery('dataFinalPesquisa'); 
				$(idData).hide();

				idGeral = getValorElementPorIdJQuery('valorPesquisa'); 
				$(idGeral).hide();
				
				addFocoAoCampo('valorInicialPesquisa'); }			
			else {  	
				$(idGeral).unmask();
				$(idGeral).unbind('keypress'); 
				$(idGeral).unbind('keyup');
				$(idGeral).unbind('focus');
				$(idGeral).val('');

				if (typeCampo == 'java.lang.Long') {
					$(idGeral).keypress(permitNumber);
				} else {
					$(idGeral).keypress(permitAlphaNumerico);
				}
				$(idGeral).show();
				
				idCondicao = getValorElementPorIdJQuery('labelCondicao');
				$(idCondicao).show();
				
				idItensCondicao = getValorElementPorIdJQuery('itensCondicao');
				$(idItensCondicao).show();

				idValor = getValorElementPorIdJQuery('valorInicialPesquisa'); 
				$(idValor).hide();

				idValor = getValorElementPorIdJQuery('valorFinalPesquisa'); 
				$(idValor).hide();

				idData = getValorElementPorIdJQuery('dataInicialPesquisa');
				$(idData).hide();
				
				idData = getValorElementPorIdJQuery('dataFinalPesquisa'); 
				$(idData).hide();
				
				addFocoAoCampo('valorPesquisa'); 							
			}			
		});
	}		
}


function permitirApenasNumero(id) {
	var id = getValorElementPorIdJQuery(id);
	$(id).keypress(permitNumber);
}

/**
 * Add Logradouro selecionado 
 * 
 * @param objeto
 */
function addLogradouroSelecionadoEntidade(objeto) {
	var bairroObj = JSON.parse(objeto);
	$("#log_id").val(logradouroObj.log_id);
	$("#log_nome").val(validaDescricao(logradouroObj.log_nome));
	addLogradouroEntidade(''+logradouroObj.log_id);
}



/**
 * Add bairro selecionado na tela de funcionrio
 * 
 * @param objeto
 */
function addBairroSelecionadoFunc(objeto) {
	var bairroObj = JSON.parse(objeto);
	$("#bai_id").val(bairroObj.bai_id);
	$("#bai_nome").val(validaDescricao(bairroObj.bai_nome));
	addBairroFunc(''+bairroObj.bai_id);
}

/**
 * Add bairro seleiconado na tela de entidade
 * 
 * @param objeto
 */
function addBairroSelecionadoEntidade(objeto) {
	var bairroObj = JSON.parse(objeto);
	$("#bai_id").val(bairroObj.bai_id);
	$("#bai_nome").val(validaDescricao(bairroObj.bai_nome));
	addBairroEntidade(''+bairroObj.bai_id);
}

function addDestinoSelecionadoMsg(objeto) {
	var entObj = JSON.parse(objeto);
	$("#usr_destino").val(entObj.ent_id);
	$("#loginDestino").val(validaDescricao(entObj.ent_login));
	addDestinoMsg(''+entObj.ent_id);
}

function addDestinoSelecionadoMsgDialog(objeto) {
	var entObj = JSON.parse(objeto);
	$("#usr_destinoMsgDialog").val(entObj.ent_id);
	$("#loginDestinoMsgDialog").val(validaDescricao(entObj.ent_login));
	addDestinoMsg(''+entObj.ent_id);
}

function addFuncSelecionadoComissao(objeto) {
	var entObj = JSON.parse(objeto);
	$("#ent_id").val(entObj.ent_id);
	$("#loginDestino").val(validaDescricao(entObj.ent_login));
	addFuncComissao(''+entObj.ent_id);
}

function addResponsavelSelecionadoTitulo(objeto) {
	var entObj = JSON.parse(objeto);
	$("#responsavelCodigo").val(entObj.ent_id);
	$("#responsavelNome").val(validaDescricao(entObj.ent_nome));
	addResponsavelTitulo(''+entObj.ent_id);
}

/**
 * Add cidade selecionada na tela de entidade
 * 
 * @param objeto
 */
function addCidadeSelecionadoEntidade(objeto) {
	var cidadeObj = JSON.parse(objeto);
	$("#cid_id").val(cidadeObj.cid_id);
	$("#cid_nome").val(validaDescricao(cidadeObj.cid_nome));
	addCidadeEntidade(''+cidadeObj.cid_id);
}


/**
 * Add cidade selecionada na tela de entidade
 * 
 * @param objeto
 */
function addCidadeSelecionadoFunc(objeto) {
	var cidadeObj = JSON.parse(objeto);
	$("#cid_id").val(cidadeObj.cid_id);
	$("#cid_nome").val(validaDescricao(cidadeObj.cid_nome));
	addCidadeFunc(''+cidadeObj.cid_id);
}


function pesquisarResponsavelPerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#responsavelNome").val('');
	 $.get("findResponsavel?codResponsavel=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var respObj = JSON.parse(resposta);
	        	$("#responsavelCodigo").val(respObj.ent_id);
	        	$("#responsavelNome").val(validaDescricao(respObj.ent_nome));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}



/**
 * Pesquisa bairro ao informar o id
 * 
 * @param id
 */
function pesquisarBairroPerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#bai_nome").val('');
	 $.get("findBairro?codBairro=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var bairroObj = JSON.parse(resposta);
	        	$("#bai_id").val(bairroObj.bai_id);
	        	$("#bai_nome").val(validaDescricao(bairroObj.bai_nome));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

function pesquisarUserDestinoPerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestino").val('');
	 $.get("findUserDestino?idEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destino").val(entidadeObj.ent_id);
	        	$("#loginDestino").val(validaDescricao(entidadeObj.usu_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

function pesquisarUserDestinoPerderFocoDialog(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestinoMsgDialog").val('');
	 $.get("findUserDestino?idEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destinoMsgDialog").val(entidadeObj.ent_id);
	        	$("#loginDestinoMsgDialog").val(validaDescricao(entidadeObj.usu_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}



/**
 * 	Retiradas para utilizar o arquivo de entidade
 * @param id
 * @returns
*/
function pesquisarUserDestinoPerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestino").val('');
	 $.get("findUserDestino?codEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destino").val(entidadeObj.ent_id);
	        	$("#loginDestino").val(validaDescricao(entidadeObj.ent_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

function pesquisarUserDestinoPerderFocoDialog(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestinoMsgDialog").val('');
	 $.get("findUserDestino?codEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destinoMsgDialog").val(entidadeObj.ent_id);
	        	$("#loginDestinoMsgDialog").val(validaDescricao(entidadeObj.ent_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}


function pesquisarEntidadePerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestino").val('');
	 $.get("findEntidade?codEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destino").val(entidadeObj.ent_id);
	        	$("#loginDestino").val(validaDescricao(entidadeObj.ent_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

function pesquisarEntidadePerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#loginDestino").val('');
	 $.get("findEntidade?idEntidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var entidadeObj = JSON.parse(resposta);
	        	$("#usr_destino").val(entidadeObj.ent_id);
	        	$("#loginDestino").val(validaDescricao(entidadeObj.usu_login));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

/**
 * Pesquisa cidade ao perder o foco informando o id
 * 
 * @param id
 */
function pesquisarCidadePerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#cid_nome").val('');
	 $.get("findCidade?codCidade=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var cidadeObj = JSON.parse(resposta);
	        	$("#cid_id").val(cidadeObj.cid_id);
	        	$("#cid_nome").val(validaDescricao(cidadeObj.cid_nome));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

/**
 * add bairro selecionado no funcionario sendo cadastrado
 * 
 * @param id
 */
function addBairroFunc(id) {
	if (id.trim() != '') {
		 $.get("addBairroFunc?codBairro=" + id);
	}
} 


function addDestinoMsg(id) {
	if (id.trim() != '') {
		 $.get("addDestinoMsg?idEntidade=" + id);
	}
} 

/**
 * Add Logradouro selecionado na entidade sendo cadastrada
 * 
 * @param id
 */
function addLogradouroEntidade(id) {
	if (id.trim() != '') {
		 $.get("addLogradouroEntidade?idLogradouro=" + id);
	}
} 

/**
 * Add bairro selecionado na entidade sendo cadastrada
 * 
 * @param id
 */
function addBairroEntidade(id) {
	if (id.trim() != '') {
		 $.get("addBairroEntidade?codBairro=" + id);
	}
} 

/**
 * Add cidade selecionada na entidade sendo cadastrada
 * 
 * @param id
 */
function addCidadeEntidade(id) {
	if (id.trim() != '') {
		 $.get("addCidadeEntidade?codCidade=" + id);
	}
} 

function verificaMsgNaoLidas() {
	 $.get("verificaMsgNaoLidas?isProcessoFacesJsf=false", function(resposta) {
		 var respObj = JSON.parse(resposta);
		 if (respObj.quantidadeMsgNaoLida != '0'){
			 	$('#avisomensagem').show();
				$('#contadormensagem').html(respObj.quantidadeMsgNaoLida);
	      } else {
				$('#avisomensagem').hide();// esconde o icone de carta
				$('#contadormensagem').html("&nbsp;");
		 }
	  }); 
} 


function alterarSenha(context) {
	 statusDialog.show();
	 	document.location =	 context + "/cadastro/alterar_senha.jsf";
	 statusDialog.hide();
}

function marcarDesmarcarLido(men_codigo, context) {
	if (men_codigo != null && men_codigo != '') {
	 $.get("marcarDesmarcarLido?men_codigo=" + men_codigo, function(resposta) {
	   })
	   .always(function() { 
		   document.location = context + "/cadastro/msg_recebidas.jsf";
	   });
	}
	else {
		alert("Selecione uma mensagem.");
	}
}

function responderMsg(context, destino) {
	 $.get("responderMsg?destino=" + destino, function(resposta) {
	   })
	   .always(function() { 
		   document.location = context + "/cadastro/cad_mensagem.jsf";
	   });
}

function copiarValorNomeRazao(campo) {
	var idCampoDestino = getValorElementPorIdJQuery('ent_razao');
	var textParaCopia = campo.value;
	
	var textCampoDestino = $(idCampoDestino).val();
	
	if (textCampoDestino === null || textCampoDestino === '' || textCampoDestino === ' '){
		$(idCampoDestino).val(textParaCopia);
	}
	
}

function confirmaLeituraMsg(men_codigo) {
	
	 $.get("confirmaLeituraMsg?men_codigo=" + men_codigo, function(resposta) {
		 // alguma a��o aqui se precisar
		 reloadPage();
		}).fail(function() {
		    alert( "Erro ao enviar confirma��o de leitura da mensagem." );
		});
	
}

// Faz com que a tecla enter tenha efeito de TAB pulando de campo em campo
function gerenciaTeclaEnter() {
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			return (code === 13) ? false : true;

		});

		$('input[type=text]').keydown(function(e) {
			// Obter o pr�ximo �ndice do elemento de entrada de texto
			var next_idx = $('input[type=text]').index(this) + 1;

			// Obter o n�mero de elemento de entrada de texto em um documento html
			var tot_idx = $('body').find('input[type=text]').length;

			// Entra na tecla no c�digo ASCII
			if (e.keyCode === 13) {
				if (tot_idx === next_idx)
					// V� para o primeiro elemento de texto
					$('input[type=text]:eq(0)').focus();
				else
					// V� para o elemento de entrada de texto seguinte
					$('input[type=text]:eq(' + next_idx + ')').focus();
			}
		});
	});

}

function processaDelete(e) {
	
	 
	/*
	 * var rows = document.getElementsByTagName('tr');
	 * 
	 * for(var x = 0, xLength = rows.length; x < xLength; x++) {
	 * 
	 * alert('rowIndex=' + rows[x].rowIndex);
	 *  }
	 */
	
	$(document).ready(function(){
	     $("table>tbody>tr").each(function(index, elemento){
	    	 
	    	 var selecionado = $(this).attr('aria-selected'); 
	    	 
	    	 if(selecionado != undefined 
	    			 && selecionado != 'undefined' 
	    			 && selecionado === 'true' || selecionado === true){
	    		 
	    	
	    		 //$(this).attr("onkeypress", "javascript:alert('kkk')");
	    		// $(this).attr("onkeydown", "javascript:alert('kkk')");
	    		 //$(this).attr("onkeyup", "javascript:alert('kkk')");
	    		 //$(this).attr("ondblclick", "javascript:alert('kkk')");
	    		 //alert($(this).attr('data-ri') + " - " + $(this).attr('data-rk')); 
	    	 }
//	         $(elemento).bind('click', function(){
	             //$(this).css('background-color', 'red');
	//         });
	     });
	});
}

function ocultaDataNacimento(value){
	  if (value === 'TIPO_PESSOA_JURIDICA'){
			
			$("#labelDataNasc").hide(); 
			
			var id = getValorElementPorIdJQuery('ent_datanascimento');
			$("#ent_datanascimento").removeClass('calendar');
			$("#ent_datanascimento").addClass('calendarOculta');
			$(id).hide();
			
			$("#msgent_datanascimento").hide();
			
	  }else {
			$("#labelDataNasc").show();
			
			var id = getValorElementPorIdJQuery('ent_datanascimento');
			$("#ent_datanascimento").removeClass('calendarOculta');
			$("#ent_datanascimento").addClass('calendar');
			$(id).show();
			
			$("#msgent_datanascimento").show();	
	  }
	}




function invocaApplet(context) {
	
	   //Faz algo com ajax...
	    
		var url = context + "/applet/imprimir.jsp?impressoraImprimir=" + null;// passando null para pegar a padr�o
		
		var title = "Imprimindo...";
		var w = "150"; 
		var h = "130";
	    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
	    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

	    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

	    var left = ((width / 2) - (w / 2)) + dualScreenLeft;  
	    var top = ((height / 2) - (h / 2)) + dualScreenTop;  
	    window.open(url, title, 'scrollbars=true, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
}

function invocaAppletFileLocal(context) {
	var url = context + "/applet/ler_file_local.jsp"; 

	var title = "Lendo arquivo local...";
	var w = "220";
	var h = "200";
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;  
    var top = ((height / 2) - (h / 2)) + dualScreenTop;  
    window.open(url, title, 'scrollbars=true, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
}

function respostaApplet(respostaProperties) {
	respostaPropertiesElement = document.getElementById("respostaPropertiesElement");
	if (respostaProperties != ''){
		respostaPropertiesElement.value = respostaProperties;
	}
	// Faz qualquer coisa aqui
}

function executeApplet(context) {
	contextApp = context;
	var attributes = {
		code : 'JalisApplet.class',
		archive : 'jalisApplet.jar',
		codebase : context + '/applet/',
		type : 'applet',
		name : 'jalisApplet',
		width : 0,
		height : 0
	};
	var parameters = {
		parametroApplet : 'parametroApplet teste valor',
		Permissions : 'all-permissions'
	};
	var version = '1.6';
	deployJava.runApplet(attributes, parameters, version);
}

/**
 * Mostra erro do applet
 * @param erro
 */
function erroApplet(erro) { 
	alert(erro);
}


/**
 * Funções criadas exclusivamente para utilização da Rabbit
 * @param datax
 * @returns
 */

function validarCampoPesquisaDataInicial(datax) {
 	if ( datax != undefined  &&  datax.value != undefined ) {
		if (datax.value() === '') {
			datax.value = '01-01-2000';
		}
	}
}

function validarCampoPesquisaDataFinal(datax) { 
 	if ( datax != undefined  &&  datax.value != undefined ) {
		if (datax.value() === '') {
			datax.value = '01-01-2099';
		}
	}
}

function validarCampoPesquisaValorInicial(valorx) {
	if ( valorx != undefined  &&  valorx.value != undefined ) {
		if (valorx.value.trim() === '') {
			valorx.value = '0';
		}else {
			valorx.value = valorx.value.trim();
		}
	}
}

function validarCampoPesquisaValorFinal(valorx) {
	if ( valorx != undefined  &&  valorx.value != undefined ) {
		if (valorx.value.trim() === '') {
			valorx.value = '99999999999';
		}else {
			valorx.value = valorx.value.trim();
		}
	}
}

/**
 * 
 * 
 *                    CRIADAS POR EDUARDO HENRIQUE DE PAULA
 * 
 * 
 * 
 */


function pesquisarLogradouroPerderFoco(id) {
	if (id.trim() != '') {
	 statusDialog.show();
	 $("#logradouroCep").val('');
	 $.get("findLogradouro?idLogradouro=" + id, function(resposta) {
	        if (resposta != 'erro' && resposta.trim() != ''){
	        	var logradouroObj = JSON.parse(resposta);
	        	$("#logradouro").val(logradouroObj.log_id);
	        	$("#logradouroCep").val(validaDescricao(logradouroObj.log_nome));
	        }
	   })
	   .always(function() { 
		   statusDialog.hide();
		});
	}
}

function addLogradouroCep(id) {
	if (id.trim() != '') {
		 $.get("addLogradouroCep?idLogradouro=" + id);
	}
} 


function addLogradouroSelecionadoCep(objeto) {
	var cepObj = JSON.parse(objeto);
	$("#logradouro").val(cepObj.log_id);
	$("#logradouroCep").val(validaDescricao(logObj.log_nome));
	addLogradouroCep(''+logObj.log_id);
}

function preencheTelaCep(objetoEnviado) {
	alert(document.getElementById(logradouro).value);
	alert("Eduardo");
	
	$(logradouro).val = objetoEnviador.logradouro;
	
}
