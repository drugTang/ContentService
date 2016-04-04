<?php
//md5签名方式--非简单签名
header("Content-Type:text/html;charset=UTF-8");
date_default_timezone_set("PRC");
$showapi_secret = '012322aa73744ee7935547cfafbe24ff';  //替换此值,在官网的"我的应用"中找到相关值
$showapi_appid = '17280';  //替换此值,在官网的"我的应用"中找到相关值
$showapi_timestamp = date('YmdHis');

$channelName = !empty($_REQUEST['channelName']) ? $_REQUEST['channelName'] : '国内';
$page = !empty($_REQUEST['page']) ? $_REQUEST['page'] : '1';
 
$paramArr = array(
     'showapi_appid' => $showapi_appid,
     'showapi_timestamp' => $showapi_timestamp,
    //添加其他参数,注意添加参数时$showapi_timestamp后要添加","逗号
	 'channelName' => $channelName,
	 'page' => $page
);
 
//创建参数(包括签名的处理)
function createParam ($paramArr,$showapi_secret) {
     $paraStr = "";
     $signStr = "";
     ksort($paramArr);
     foreach ($paramArr as $key => $val) {
         if ($key != '' && $val != '') {
             $signStr .= $key.$val;
             $paraStr .= $key.'='.urlencode($val).'&';
         }
     }
     $signStr .= $showapi_secret;//排序好的参数加上secret,进行md5
     $sign = strtolower(md5($signStr));
     $paraStr .= 'showapi_sign='.$sign;//将md5后的值作为参数,便于服务器的效验
     //echo "排序好的参数:".$paraStr."<br/>";
     return $paraStr;
}
 
$param = createParam($paramArr,$showapi_secret);
$url = 'http://route.showapi.com/109-35?'.$param; 
//echo "请求的url:".$url."<br>";
$result = file_get_contents($url);
echo $result;

//$jsmary = json_decode($result);
//echo $jsmary;
//echo "取出showapi_res_code的值:";
//print_r($result->showapi_res_code);
//echo "$result->showapi_res_code <br>";

?>
