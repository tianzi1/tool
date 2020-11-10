<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
</head>
<body>
<table>
    <th>表结构导出</th>
    <tbody>
   <#list tables as table>
       <tr>
           <td>${table}</td>
           <td> <a href="/getTableStructToExcel?table=${table}"><button >下载表结构</button></a></td>
       </tr>
   </#list>
    </tbody>
</table>
</body>
</html>