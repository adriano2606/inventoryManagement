<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>FiapStore</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<fmt:setLocale value="pt_BR"/>
<%@include file="header.jsp"%>
<div class="container">
    <div class="mt-5 ms-5 me-5">

        <div class="card mb-3">
            <div class="card-header">
                LISTA DE PRODUTOS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão de produdos eficiente</h5>
                <p class="card-text">Mantenha os dados dos seus produtos sempre atualizados e acessíveis.</p>

                <c:if test="${not empty msg}">
                    <div class="alert alert-success ms-2 me-2 mt-2 m-auto">${msg}</div>
                </c:if>

                <c:if test="${not empty erro}">
                    <div class="alert alert-danger ms-2 me-2 mt-2 m-auto">${erro}</div>
                </c:if>


                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th class="text-center">Valor</th>
                        <th class="text-center">Quantidade</th>
                        <th class="text-center">Data de fabricação</th>
                        <th class="text-center">Categoria</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${produtos}" var="produto" >


                        <tr>
                            <td>${produto.codigo}</td>
                            <td>${produto.nome}</td>
                            <td><fmt:formatNumber value="${produto.valor}" type="currency" minFractionDigits="2"/></td>
                            <td>${produto.quantidade}</td>

                            <fmt:parseDate value="${produto.dataFabricacao}" type="date" pattern="yyyy-mm-dd" var="parsedDate"/>
                            <td><fmt:formatDate value="${parsedDate}" type="date" pattern="dd/mm/yyyy"/></td>

                            <td>${produto.nomeCategoria}</td>
                            <td class="text-center">
                                <c:url value="produtos" var="link">
                                    <c:param name="acao" value="abrir-form-edicao"/>
                                    <c:param name="codigo" value="${produto.codigo}"/>
                                </c:url>
                                <a href="${link}" class="btn btn-primary">Editar</a>

                                <!-- Botão para abrir a modal -->
                                <button
                                        type="button"
                                        class="btn btn-danger"
                                        data-bs-toggle="modal"
                                        data-bs-target="#excluirModal"
                                        onclick="codigoExcluir.value = ${produto.codigo}"
                                >
                                    Excluir
                                </button>

                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
                <a href="produtos?acao=abrir-form-cadastro" class="btn btn-primary">Adicionar produto</a>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div
        class="modal fade"
        id="excluirModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="exampleModalLabel">
                    Confirmar Exclusão
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <h4>Você confirma a exclusão deste produto?</h4>
                <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
            </div>
            <div class="modal-footer">

                <form action="produtos" method="post">
                    <input
                            type="hidden"
                            name="acao"
                            value="excluir">
                    <input
                            type="hidden"
                            name="codigoExcluir"
                            id="codigoExcluir">
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Não
                    </button>
                    <button
                            type="submit"
                            class="btn btn-danger">
                        Sim
                    </button>
                </form>

            </div>
        </div>
    </div>
</div>
<%--    fim modal--%>




<%@include file="footer.jsp"%>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>