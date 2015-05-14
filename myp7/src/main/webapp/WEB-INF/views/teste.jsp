<!doctype html>
<html>
   <head>
      <style>
         a {
            text-decoration: none;
            color: #a13;
            font-weight: bold;
         }
         a:hover {
            text-decoration: underline;
         }
         pre {
            font-family: "Ubuntu Mono";
            font-size: 0.9em;
            width: 75%;
            background-color: #007143;
            border-radius: 6px; 
            padding: 5px 10px;
            color: #fff;
            cursor: pointer;
            text-shadow: -1px 1px 1px #333;
            box-shadow: 0px 8px 8px -8px #333;
            -moz-transition: all 0.3s ease-out;
            -webkit-transition: all 0.3s ease-out;
            -ms-transition: all 0.3s ease-out;
            -o-transition: all 0.3s ease-out;
            transition: all 0.3s ease-out;
         }
         pre:hover {
            background-color: #00ae68;
         }
      </style>
      <meta http-equiv="X-UA-Compatible" content="IE=Edge">
      <link rel='stylesheet' href='resources/css/jackedup.css'/>
      <script src='resources/js/humane.min.js'></script>
   </head>
   <body>

      <pre>humane.log("Sistema Myp7")</pre>

      <script>
         humane.baseCls = 'humane-jackedup'
         var pretags = document.getElementsByTagName('pre')
         for (var i = 0; i < pretags.length; i++) { (function(el){
            el.onclick = function () {
               eval(el.innerHTML)
            }
         }(pretags[i])) }
      </script>
   </body>
</html>
