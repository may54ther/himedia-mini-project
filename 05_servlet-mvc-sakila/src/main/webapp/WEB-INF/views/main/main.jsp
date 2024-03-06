<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인페이지</title>
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
    <p>조회할 고객의 번호를 입력하세요.</p>
    <form action="${pageContext.servletContext.contextPath}/customer/select">
        <table>
            <tbody>
                <tr>
                    <th>
                        <label for="customerId">고객 번호</label>
                    </th>
                    <td>
                        <input type="text" id="customerId" name="customerId">
                    </td>
                </tr>
            </tbody>
        </table>
        <button>고객 조회</button>
    </form>

    <hr />

    <h1>고객 목록</h1>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/customer/list'">목록 조회</button>
    <hr />

    <h1>고객 추가</h1>
    <form action="${ pageContext.servletContext.contextPath }/customer/insert" method="post">
        <table>
            <tbody>
                <tr>
                    <th>
                        <label for="storeId">상점 번호</label>
                    </th>
                    <td>
                        <select id="storeId" name="storeId">
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="firstName">이름</label>
                    </th>
                    <td>
                        <input type="text" id="firstName" name="firstName">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="lastName">성</label>
                    </th>
                    <td>
                        <input type="text" id="lastName" name="lastName">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="email">이메일</label>
                    </th>
                    <td>
                        <input type="text" id="email" name="email">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="addressId">주소 식별코드</label>
                    </th>
                    <td>
                        <input type="text" id="addressId" name="addressId" placeholder="1 ~ 501 사이의 값을 입력">
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit">등록</button>
    </form>

    <hr />

    <h1>고객 정보 수정</h1>
    <form action="${ pageContext.servletContext.contextPath }/customer/update" method="post">
        <table>
            <tbody>
            <tbody>
                <tr>
                    <th>
                        <label for="updateCustomerId">고객번호</label>
                    </th>
                    <td>
                        <input type="text" id="updateCustomerId" name="customerId">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="updateFirstName">이름</label>
                    </th>
                    <td>
                        <input type="text" id="updateFirstName" name="firstName">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="updateLastName">성</label>
                    </th>
                    <td>
                        <input type="text" id="updateLastName" name="lastName">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="updateEmail">이메일</label>
                    </th>
                    <td>
                        <input type="text" id="updateEmail" name="email">
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit">변경</button>
    </form>

    <hr />

    <h1>고객 정보 삭제</h1>
    <form action="${ pageContext.servletContext.contextPath }/customer/delete" method="post">
        <table>
            <tbody>
                <tr>
                    <th>
                        <label for="deleteCustomerId">고객 번호</label>
                    </th>
                    <td>
                        <input type="text" id="deleteCustomerId" name="customerId">
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit">삭제</button>
    </form>
</body>
</html>

