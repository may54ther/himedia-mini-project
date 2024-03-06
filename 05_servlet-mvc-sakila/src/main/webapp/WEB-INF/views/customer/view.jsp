<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>고객 조회</title>
    <style>
        table, tr, td, th {border: 1px solid #e4e4e4; border-collapse: collapse; border-spacing: 0; font-size: 14px; text-align: left}
        th, td {padding: 4px 8px; font-weight: normal;}
        th {background: #f3f3f3;}
        input, select {width: 300px; height: 24px; border: 1px solid #e4e4e4; box-sizing: border-box }
        button {width: 80px; height: 26px; margin-top: 7px; color: #fff; font-size: 12px; line-height: 26px; border: 0; background: #444; border-radius: 2px}
    </style>
</head>
<body>
    <h1>고객 조회</h1>
    <table>
        <thead>
            <tr>
                <th>고객 번호</th>
                <td>${ customer.customerId }</td>
            </tr>
            <tr>
                <th>상점 번호</th>
                <td>${ customer.storeId }</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${ customer.firstName }</td>
            </tr>
            <tr>
                <th>성(</th>
                <td>${ customer.lastName }</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${ customer.email }</td>
            </tr>
            <tr>
                <th>주소 식별코드</th>
                <td>${ customer.addressId }</td>
            </tr>
        </thead>
    </table>
</body>
</html>
