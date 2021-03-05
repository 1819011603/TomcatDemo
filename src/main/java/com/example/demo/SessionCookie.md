Session和Cookie的区别
从存储方式上比较

Cookie只能存储字符串，如果要存储非ASCII字符串还要对其编码。
Session可以存储任何类型的数据，可以把Session看成是一个容器
从隐私安全上比较

Cookie存储在浏览器中，对客户端是可见的。信息容易泄露出去。如果使用Cookie，最好将Cookie加密
Session存储在服务器上，对客户端是透明的。不存在敏感信息泄露问题。
从有效期上比较

Cookie保存在硬盘中，只需要设置maxAge属性为比较大的正整数，即使关闭浏览器，Cookie还是存在的
Session的保存在服务器中，设置maxInactiveInterval属性值来确定Session的有效期。并且Session依赖于名为JSESSIONID的Cookie，该Cookie默认的maxAge属性为-1。如果关闭了浏览器，该Session虽然没有从服务器中消亡，但也就失效了。
从对服务器的负担比较

Session是保存在服务器的，每个用户都会产生一个Session，如果是并发访问的用户非常多，是不能使用Session的，Session会消耗大量的内存。
Cookie是保存在客户端的。不占用服务器的资源。像baidu、Sina这样的大型网站，一般都是使用Cookie来进行会话跟踪。
从浏览器的支持上比较

如果浏览器禁用了Cookie，那么Cookie是无用的了！
如果浏览器禁用了Cookie，Session可以通过URL地址重写来进行会话跟踪。
从跨域名上比较

Cookie可以设置domain属性来实现跨域名
Session只在当前的域名内有效，不可夸域名


那么，什么时候才需要同时使用Cookie和Session呢？
在上一篇博客中，我们使用了Session来进行简单的购物，功能也的确实现了。现在有一个问题：我在购物的途中，不小心关闭了浏览器。当我再返回进去浏览器的时候，发现我购买过的商品记录都没了！！为什么会没了呢？原因也非常简单：服务器为Session自动维护的Cookie的maxAge属性默认是-1的，当浏览器关闭掉了，该Cookie就自动消亡了。当用户再次访问的时候，已经不是原来的Cookie了。
我们现在想的是：即使我不小心关闭了浏览器了，我重新进去网站，我还能找到我的购买记录。
要实现该功能也十分简单，问题其实就在：服务器为Session自动维护的Cookie的maxAge属性是-1，Cookie没有保存在硬盘中。我现在要做的就是：把Cookie保存在硬盘中，即使我关闭了浏览器，浏览器再次访问页面的时候，可以带上Cookie，从而服务器识别出Session。

第一种方式：只需要在处理购买页面上创建Cookie，Cookie的值是Session的id返回给浏览器即可

~~~

        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(30*60);
        cookie.setPath("/ouzicheng/");
        response.addCookie(cookie);
~~~

