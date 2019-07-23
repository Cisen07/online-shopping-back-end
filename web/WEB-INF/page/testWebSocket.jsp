<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
 
<!DOCTYPE HTML>
<html>
 
<head>
       
    <base href="<%=basePath%>">
        <title>My WebSocket</title>
     
</head>

<body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</body>

<body>
    Welcome<br/>
<h1>这个页面给所有登录的用户推送消息</h1>
    <input id="text" type="text"/>
<button id="btn_send" οnclick="send()">Send</button>
   
<button id="btn_close" οnclick="closeWebSocket()">Close</button>
   
<div id="message">
       
</div>
 
</body>
   
<script type="text/javascript">
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8081/chat");
        alert("当前浏览器支持WebSocket");
        console.log(websocket);
    } else {
        alert('Not support websocket');
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        setMessageInnerHTML("open");
    };

    //接收到消息的回调方法
    websocket.onmessage = function () {
        alert("接收到消息");
        setMessageInnerHTML(event.data);
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("close");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    };

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭连接
    $('#btn_close').click(function(){
        alert('test2');
        websocket.close();
    });

    //发送消息
    $('#btn_send').click(function(){
        alert('test');
        var message = document.getElementById('text').value;
        websocket.send(message);
    });

</script>
</html>