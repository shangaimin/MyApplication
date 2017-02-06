var gBridge=null;
function connectWebViewJavascriptBridge(callback) {
        if (window.WebViewJavascriptBridge) {
      callback(WebViewJavascriptBridge)
    } else {
      document.addEventListener('WebViewJavascriptBridgeReady', function() {
                                callback(WebViewJavascriptBridge)
                                }, false)
    }
}
function registerMoreCommentAction(bridge){
  var item = document.getElementById('moreComment');
  if(item == null)
    return;
  item.onclick = function(e){
    e.preventDefault();
    bridge.callHandler('moreComment', null, function(response){});
  }
}
function registerShareActions(bridge){
  registerShareAction(bridge, "shareToWeixin", 'WeiXin', function(response){});
  registerShareAction(bridge, "shareToPenYouQuan", 'PenYouQuan', function(response){});
  registerShareAction(bridge, "shareToWeiBo", 'WeiBo', function(response){});
  registerShareAction(bridge, "shareToMore", 'More', function(response){});
}
function registerShareAction(bridge, id, platform, callback){
  var item = document.getElementById(id);
  if(item == null)
    return;
  item.onclick = function(e){
    e.preventDefault();
    bridge.callHandler('shareAction', {'platform': platform}, function(response) {})
  }
}
function readArticleAction(bridge, articleId){
  bridge.callHandler('readArticleAction', {"articleId":articleId}, function(response){});
}
function showProductAction(bridge, productId){
    bridge.callHandler("showProductAction", {"productId":productId}, function(response){});
}
/*
 相关文章
 */

function buildRelatedArticles(articlesAndTags){
     addRelatedArticlesHeader();
     articles = articlesAndTags["articles"]
     tags = articlesAndTags["tags"]
     if(tags.length>0)
        buildArticleTags(tags);
     for (var i = 0,len = articles.length; i < len; i++) {
         var article = articles[i];
         var img    = article["img"];
         var title    = article["title"];
         var time = article["time"];
         var articleId = article["id"];
         buildRelatedArticle(articleId, img,title,time);
     };

        var article_section = document.getElementById("article_section");
           article_section.style.display = 'block';
}

function addRelatedArticlesHeader(){
  var headerHTML = '<p class="related">相关文章：</p><div class="tag-list"><ul id="tag_list" style="display:none"><div class="clear"></div></ul></div><ul id="article"></ul>';
  document.getElementById('article_section').innerHTML = headerHTML;
  registerMoreCommentAction(gBridge);
}


function buildArticleTags(tags){
    var list = document.getElementById('tag_list');
    list.style.display = 'block';
    for (var i = 0,len = tags.length; i < len; i++) {
        var tag = tags[i];
        var floor = document.createElement('li');
        floor.innerHTML = "<a href='javascript:void(0)'>" + tag +"</a>"
        list.appendChild(floor);
    }
    var cleanDiv = document.createElement('div');
    cleanDiv.className = 'clear';
    list.appendChild(cleanDiv)
}

function buildRelatedArticle(articleId, img, title, time){
  var floor = document.createElement('li');
  floor.addEventListener('click', function(event){readArticleAction(gBridge, articleId);}, false);

  var innerHTML = '<p class="pic"><a href="javascript:void(0)"><img src="' + img + '" alt=""></a></p>' +
                  '<p class="words"><a href="javascript:void(0)">' + title + '</a></p>' +
                  '<p class="time">' + time + '</p><div class="clear"></div>';
  floor.innerHTML = innerHTML;
  var list = document.getElementById('article');
  list.appendChild(floor);
}
/*
 相关产品
 */
function addRelatedProductHeader(){
  var headerHTML = '<p class="related">相关产品：</p><ul id="product"></ul>';
  document.getElementById('products_section').innerHTML = headerHTML;
  registerMoreCommentAction(gBridge);
}
function buildRelatedProducts(products){
  addRelatedProductHeader();
  for (var i = 0,len = products.length; i < len; i++) {
    var product = products[i];
    var img = product["img"];
    var productName = product["name"];
    var productId = product["id"];
    buildRelatedProduct(productId, img, productName);
  };
   var products_section = document.getElementById("products_section");
          products_section.style.display = 'block';
}
function buildRelatedProduct(productId, img, productName){
  var floor = document.createElement('li');
  floor.addEventListener('click', function(event){
                                 showProductAction(gBridge, productId);
                                 }, false);

  var innerHTML = '<p class="pic"><a href="javascript:void(0)"><img src="' + img + '" alt=""></a></p>' +
                  '<p class="words"><a href="javascript:void(0)">' + productName + '</a></p><div class="clear"></div>';
  floor.innerHTML = innerHTML;
  var list = document.getElementById('product');
  list.appendChild(floor);
}

function showContent(data){
   var loading_section = document.getElementById("loading_section");
   loading_section.style.display = 'none';
   var titleSectionDiv = document.getElementById("title_section");
   titleSectionDiv.style.backgroundColor = data['titleColor']; ;
   titleSectionDiv.style.display = 'block';
   var titleDiv = document.getElementById("title");
   titleDiv.innerHTML = data["title"]
   var subTitleDiv = document.getElementById("subtitle");
   subTitleDiv.innerHTML = data["subTitle"]
   var bodySectionDiv = document.getElementById("content");
   bodySectionDiv.innerHTML = data["content"];
   bodySectionDiv.style.display = 'block'
   var footerSectionDiv = document.getElementById("footer_section");
   footerSectionDiv.style.display = 'block';
   var failSectionDiv = document.getElementById("fail_section");
   failSectionDiv.style.display = 'none';
}

function showContentWithOutTitle(data){
   var loading_section = document.getElementById("loading_section");
   loading_section.style.display = 'none';
   var titleSectionDiv = document.getElementById("title_section");
   titleSectionDiv.style.display = 'none';
   var bodySectionDiv = document.getElementById("content");
   bodySectionDiv.innerHTML = data["content"];
   bodySectionDiv.style.display = 'block'
   var footerSectionDiv = document.getElementById("footer_section");
   footerSectionDiv.style.display = 'none';
   var failSectionDiv = document.getElementById("fail_section");
   failSectionDiv.style.display = 'none';
}

function showLoading(){
    var loading_section = document.getElementById("loading_section");
    loading_section.style.display = 'block';
    var titleSectionDiv = document.getElementById("title_section");
    titleSectionDiv.style.display = 'none';
    var bodySectionDiv = document.getElementById("content");
    bodySectionDiv.style.display = 'none';
    var footerSectionDiv = document.getElementById("footer_section");
    footerSectionDiv.style.display = 'none';
    var failSectionDiv = document.getElementById("fail_section");
    failSectionDiv.style.display = 'none';
}
function showBlankPage(){
    var loading_section = document.getElementById("loading_section");
    loading_section.style.display = 'none';
    var titleSectionDiv = document.getElementById("title_section");
    titleSectionDiv.style.display = 'none';
    var bodySectionDiv = document.getElementById("content");
    bodySectionDiv.style.display = 'none';
    var footerSectionDiv = document.getElementById("footer_section");
    footerSectionDiv.style.display = 'none';
    var failSectionDiv = document.getElementById("fail_section");
    failSectionDiv.style.display = 'block';
}

function clearImage(element)
{
  element.style.display = "none";
}
connectWebViewJavascriptBridge(function(bridge) {
  var uniqueId = 1
  bridge.init(function(message, responseCallback) {
    var data = { 'Javascript Responds':'Wee!' }
    responseCallback(data)
  });
  gBridge = bridge;
  registerShareActions(bridge);

  bridge.registerHandler('buildFloors', function(data, responseCallback) {buildFloors(data)});
  bridge.registerHandler('buildRelatedArticles', function(data, responseCallback) { buildRelatedArticles(data)});
  bridge.registerHandler('buildRelatedProducts', function(data, responseCallback) { buildRelatedProducts(data)});
  bridge.registerHandler('showContent', function(data, responseCallback) { showContent(data)});
  bridge.registerHandler('showContentWithOutTitle', function(data, responseCallback) { showContentWithOutTitle(data)});
  bridge.registerHandler('showLoading', function(data, responseCallback) { showLoading()});
  bridge.registerHandler('showBlankPage', function(data, responseCallback){showBlankPage()});
})