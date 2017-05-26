define(function (){

return {
	init: function (){
		var signHtml = '\
			<div class="sign">\
			    <div class="sign-mask"></div>\
			    <div class="container">\
			        <a href="#" class="close-link signclose-loader"><i class="fa fa-close"></i></a>\
			        <div class="sign-tips"></div>\
			        <form id="sign-in">  \
			            <h3><small class="signup-loader">切换注册</small>登录</h3>\
			            <h6>\
			                <label for="inputEmail">邮箱</label>\
			                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="登录邮箱">\
			            </h6>\
			            <h6>\
			                <label for="inputPassword">密码</label>\
			                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="登录密码">\
			            </h6>\
			            <div class="sign-submit">\
			                <input type="button" class="btn btn-primary signsubmit-loader" name="submit" value="登录">  \
			                <input type="hidden" name="action" value="signin">\
			                <label><input type="checkbox" checked="checked" name="remember" value="forever">记住我</label>\
			            </div>'+(jsui.url_rp ? '<div class="sign-info"><a href="'+jsui.url_rp+'">找回密码？</a></div>' : '')+
			        '</form>\
			        <form id="sign-up"> \
			            <h3><small class="signin-loader">切换登录</small>注册</h3>\
			            <h6>\
			                <label for="inputName">昵称</label>\
			                <input type="text" name="name" class="form-control" id="inputName" placeholder="设置昵称">\
			            </h6>\
			            <h6>\
			                <label for="inputEmail">邮箱</label>\
			                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="邮箱">\
			            </h6>\
			            <h6>\
			                <label for="inputPassword">密码</label>\
			                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="设置登录密码">\
			            </h6>\
			            <div class="sign-submit">\
			                <input type="button" class="btn btn-primary btn-block signsubmit-loader" name="submit" value="快速注册">  \
			                <input type="hidden" name="action" value="signup">  \
			            </div>\
			        </form>\
			    </div>\
			</div>\
		'

	    jsui.bd.append( signHtml )

	    if( $('#issignshow').length ){
	        jsui.bd.addClass('sign-show')
	        $('.close-link').hide()
	        $('#sign-in').show().find('input:first').focus()
	        $('#sign-up').hide()
	    }


	    $('.signin-loader').on('click', function(){
	    	jsui.bd.addClass('sign-show')
            $('#sign-in').show().find('input:first').focus()
            $('#sign-up').hide()
	    })

	    $('.signup-loader').on('click', function(){
	    	jsui.bd.addClass('sign-show')
            $('#sign-up').show().find('input:first').focus()
            $('#sign-in').hide()
	    })

	    $('.signclose-loader').on('click', function(){
	    	jsui.bd.removeClass('sign-show')
	    })

	    $('.signsubmit-loader').on('click', function(){
	    	if( jsui.is_signin ) return
                    
            var form = $(this).parent().parent()
            var inputs = form.serializeObject()
            var isreg = (inputs.action == 'signup') ? true : false

            if( !inputs.action ){
                return
            }

            if( !is_mail(inputs.email) ){
                logtips('邮箱格式错误')
                return
            }

            if( inputs.password.length < 6 ){
                logtips('密码太短，至少6位')
                return
            }

            if( isreg ){
                if( !is_name(inputs.name) ){
                    logtips('昵称限制在3-20字')
                    return
                }
            }

            $.ajax({  
                type: "POST",  
                url:  jsui.uri+'/action/log.php',  
                data: inputs,  
                dataType: 'json',
                success: function(data){  
                    // console.log( data )
                    if( data.error ){
                        if( data.msg ){
                            logtips(data.msg)
                        }
                        return
                    }

                    if( !isreg ){
                        location.reload()
                    }else{
                        if( data.goto ) location.href = data.goto
                    }
                }  
            });  
	    })

		var _loginTipstimer
		function logtips(str){
		    if( !str ) return false
		    _loginTipstimer && clearTimeout(_loginTipstimer)
		    $('.sign-tips').html(str).animate({
		        height: 29
		    }, 220)
		    _loginTipstimer = setTimeout(function(){
		        $('.sign-tips').animate({
		            height: 0
		        }, 220)
		    }, 5000)
		}

	}
}

})