<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>고객 목록</title>
    <style>
        table, tr, td, th {border: 1px solid #e4e4e4; border-collapse: collapse; border-spacing: 0; font-size: 14px; text-align: left}
        th, td {padding: 4px 8px; font-weight: normal;}
        th {background: #f3f3f3;}
        input, select {width: 300px; height: 24px; border: 1px solid #e4e4e4; box-sizing: border-box }
        button {width: 80px; height: 26px; margin-top: 7px; color: #fff; font-size: 12px; line-height: 26px; border: 0; background: #444; border-radius: 2px}
    </style>
</head>
<body>
    <h1>고객 목록</h1>

    <table class="tb">
        <thead>
            <tr>
                <th>고객 번호</th>
                <th>상점 번호</th>
                <th>이름</th>
                <th>성</th>
                <th>이메일</th>
                <th>주소 식별코드</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>${ customer.customerId }</td>
                    <td>${ customer.storeId }</td>
                    <td>${ customer.firstName }</td>
                    <td>${ customer.lastName }</td>
                    <td>${ customer.email }</td>
                    <td>${ customer.addressId }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
