/*
* Login界面JavaScript
* Author：lipeng
* Time：2020/02/15
* */
$(document).ready(function(){

        /*$("#check2").click(function(){
            var flag=$('#check2').prop('checked');
            if(flag){
                var userName=$("#login-username").val();
                var passWord=$("#login-password").val();
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/powers/pow/regUsers",
                    data:{"userName":userName,"passWord":passWord},
                    async:true,
                    success:function(res){
                        alert(res);
                    }
                });
            }
        })*/


    //弹出框
    function narnMessage (type) {
        naranja()[type]({
            title: 'ERROR',
            text: 'User name and password do not match',
            timeout: 'keep',
            buttons: [{
                text: 'Confirm ',
                click: function (e) {
                    e.closeNotification();
                }
            }]
        })
    }

    if(document.getElementById("message").textContent != ""){
        narnMessage("error");
    }
});