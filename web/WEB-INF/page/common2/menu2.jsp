<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>学员信息</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <%--<li><a href="${pageContext.request.contextPath}/student/list"><i class="fa fa-circle-o"></i>信息查询</a></li>--%>
                    <li><a href="#"><i class="fa fa-circle-o"></i>信息查询</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>