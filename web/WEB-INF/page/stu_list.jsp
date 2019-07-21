<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Data Tables</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/dist/css/skins/_all-skins.min.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="/WEB-INF/page/common/header.jsp"/>

    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="/WEB-INF/page/common/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                学生信息表
                <small>advanced tables</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Data tables</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <table id="stuTable" class="table">
                        <thead>
                            <tr>
                                <th>姓名</th>
                                <th>学号</th>
                                <th>性别</th>
                                <th>生日</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allStu}" var="stu">
                            <tr id="${stu.id}">
                                <th>${stu.name}</th>
                                <th>${stu.stunumber}</th>
                                <th>${stu.sexTxt}</th>
                                <th>${stu.birthdayTxt}</th>
                                <th>
                                    <button class="btn btn-danger btn-xs btn-flat btn_del">删除</button>
                                    <a class="btn btn-link btn-xs btn-flat btn_del" href="${pageContext.request.contextPath}/student/toupdate?id=${stu.id}"><i class="fa fa-edit"></i>更改</a>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>

    <!-- /.content-wrapper -->
    <jsp:include page="/WEB-INF/page/common/footer.jsp"/>

</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/adminlte/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/adminlte/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/adminlte/dist/js/demo.js"></script>
<!-- layer -->
<script src="${pageContext.request.contextPath}/adminlte/bower_components/layer-v3.1.1/layer/layer.js"></script>
<script>
    $(function () {
        $('#stuTable').DataTable({
            'paging': true,
            'lengthChange': true,
            'searching': true,
            'ordering': true,
            'info': true,
            'autoWidth': true,
            'stateSave': true,
            "language": {
                "paginate": {
                    "next": "下一页",
                    "previous": "上一页"
                },
                "search": "快速搜索",
                "info": "第_PAGE_页(共_PAGES_页)",
                "emptyTable": "无可用数据",
                "lengthMenu": "_MENU_ 条/页"
            }
        });
        $('.content').on("click", ".btn_del", function () {
                var $btnDel = $(this);
                var $tr = $(this).parents("tr");
                var idValue = $tr.attr("id");
                var currentName = $tr.find("th:eq(0)").html();
                layer.confirm('确定删除【' + currentName + '】', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/student/del",
                        data: {id: idValue},
                        success: function (msg) {
                            if (msg["ok"]) {
                                layer.msg("操作成功", {time: 700}, function () {
                                    $tr.css("background", "#889");
                                    $btnDel.remove();
                                })
                            }
                        },
                        dataType: "json",
                    })
                    layer.close(index);
                });
            }
        );
    })
</script>
</body>
</html>