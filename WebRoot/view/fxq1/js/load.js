//MooTools, <http://mootools.net>, My Object Oriented (JavaScript) Tools. Copyright (c) 2006-2009 Valerio Proietti, <http://mad4milk.net>, MIT Style License.

var MooTools={version:"1.2.3",build:"4980aa0fb74d2f6eb80bcd9f5b8e1fd6fbb8f607"};var Native=function(k){k=k||{};var a=k.name;var i=k.legacy;var b=k.protect;
var c=k.implement;var h=k.generics;var f=k.initialize;var g=k.afterImplement||function(){};var d=f||i;h=h!==false;d.constructor=Native;d.$family={name:"native"};
if(i&&f){d.prototype=i.prototype;}d.prototype.constructor=d;if(a){var e=a.toLowerCase();d.prototype.$family={name:e};Native.typize(d,e);}var j=function(n,l,o,m){if(!b||m||!n.prototype[l]){n.prototype[l]=o;
}if(h){Native.genericize(n,l,b);}g.call(n,l,o);return n;};d.alias=function(n,l,p){if(typeof n=="string"){var o=this.prototype[n];if((n=o)){return j(this,l,n,p);
}}for(var m in n){this.alias(m,n[m],l);}return this;};d.implement=function(m,l,o){if(typeof m=="string"){return j(this,m,l,o);}for(var n in m){j(this,n,m[n],l);
}return this;};if(c){d.implement(c);}return d;};Native.genericize=function(b,c,a){if((!a||!b[c])&&typeof b.prototype[c]=="function"){b[c]=function(){var d=Array.prototype.slice.call(arguments);
return b.prototype[c].apply(d.shift(),d);};}};Native.implement=function(d,c){for(var b=0,a=d.length;b<a;b++){d[b].implement(c);}};Native.typize=function(a,b){if(!a.type){a.type=function(c){return($type(c)===b);
};}};(function(){var a={Array:Array,Date:Date,Function:Function,Number:Number,RegExp:RegExp,String:String};for(var h in a){new Native({name:h,initialize:a[h],protect:true});
}var d={"boolean":Boolean,"native":Native,object:Object};for(var c in d){Native.typize(d[c],c);}var f={Array:["concat","indexOf","join","lastIndexOf","pop","push","reverse","shift","slice","sort","splice","toString","unshift","valueOf"],String:["charAt","charCodeAt","concat","indexOf","lastIndexOf","match","replace","search","slice","split","substr","substring","toLowerCase","toUpperCase","valueOf"]};
for(var e in f){for(var b=f[e].length;b--;){Native.genericize(a[e],f[e][b],true);}}})();var Hash=new Native({name:"Hash",initialize:function(a){if($type(a)=="hash"){a=$unlink(a.getClean());
}for(var b in a){this[b]=a[b];}return this;}});Hash.implement({forEach:function(b,c){for(var a in this){if(this.hasOwnProperty(a)){b.call(c,this[a],a,this);
}}},getClean:function(){var b={};for(var a in this){if(this.hasOwnProperty(a)){b[a]=this[a];}}return b;},getLength:function(){var b=0;for(var a in this){if(this.hasOwnProperty(a)){b++;
}}return b;}});Hash.alias("forEach","each");Array.implement({forEach:function(c,d){for(var b=0,a=this.length;b<a;b++){c.call(d,this[b],b,this);}}});Array.alias("forEach","each");
function $A(b){if(b.item){var a=b.length,c=new Array(a);while(a--){c[a]=b[a];}return c;}return Array.prototype.slice.call(b);}function $arguments(a){return function(){return arguments[a];
};}function $chk(a){return !!(a||a===0);}function $clear(a){clearTimeout(a);clearInterval(a);return null;}function $defined(a){return(a!=undefined);}function $each(c,b,d){var a=$type(c);
((a=="arguments"||a=="collection"||a=="array")?Array:Hash).each(c,b,d);}function $empty(){}function $extend(c,a){for(var b in (a||{})){c[b]=a[b];}return c;
}function $H(a){return new Hash(a);}function $lambda(a){return($type(a)=="function")?a:function(){return a;};}function $merge(){var a=Array.slice(arguments);
a.unshift({});return $mixin.apply(null,a);}function $mixin(e){for(var d=1,a=arguments.length;d<a;d++){var b=arguments[d];if($type(b)!="object"){continue;
}for(var c in b){var g=b[c],f=e[c];e[c]=(f&&$type(g)=="object"&&$type(f)=="object")?$mixin(f,g):$unlink(g);}}return e;}function $pick(){for(var b=0,a=arguments.length;
b<a;b++){if(arguments[b]!=undefined){return arguments[b];}}return null;}function $random(b,a){return Math.floor(Math.random()*(a-b+1)+b);}function $splat(b){var a=$type(b);
return(a)?((a!="array"&&a!="arguments")?[b]:b):[];}var $time=Date.now||function(){return +new Date;};function $try(){for(var b=0,a=arguments.length;b<a;
b++){try{return arguments[b]();}catch(c){}}return null;}function $type(a){if(a==undefined){return false;}if(a.$family){return(a.$family.name=="number"&&!isFinite(a))?false:a.$family.name;
}if(a.nodeName){switch(a.nodeType){case 1:return"element";case 3:return(/\S/).test(a.nodeValue)?"textnode":"whitespace";}}else{if(typeof a.length=="number"){if(a.callee){return"arguments";
}else{if(a.item){return"collection";}}}}return typeof a;}function $unlink(c){var b;switch($type(c)){case"object":b={};for(var e in c){b[e]=$unlink(c[e]);
}break;case"hash":b=new Hash(c);break;case"array":b=[];for(var d=0,a=c.length;d<a;d++){b[d]=$unlink(c[d]);}break;default:return c;}return b;}var Browser=$merge({Engine:{name:"unknown",version:0},Platform:{name:(window.orientation!=undefined)?"ipod":(navigator.platform.match(/mac|win|linux/i)||["other"])[0].toLowerCase()},Features:{xpath:!!(document.evaluate),air:!!(window.runtime),query:!!(document.querySelector)},Plugins:{},Engines:{presto:function(){return(!window.opera)?false:((arguments.callee.caller)?960:((document.getElementsByClassName)?950:925));
},trident:function(){return(!window.ActiveXObject)?false:((window.XMLHttpRequest)?5:4);},webkit:function(){return(navigator.taintEnabled)?false:((Browser.Features.xpath)?((Browser.Features.query)?525:420):419);
},gecko:function(){return(document.getBoxObjectFor==undefined)?false:((document.getElementsByClassName)?19:18);}}},Browser||{});Browser.Platform[Browser.Platform.name]=true;
Browser.detect=function(){for(var b in this.Engines){var a=this.Engines[b]();if(a){this.Engine={name:b,version:a};this.Engine[b]=this.Engine[b+a]=true;
break;}}return{name:b,version:a};};Browser.detect();Browser.Request=function(){return $try(function(){return new XMLHttpRequest();},function(){return new ActiveXObject("MSXML2.XMLHTTP");
});};Browser.Features.xhr=!!(Browser.Request());Browser.Plugins.Flash=(function(){var a=($try(function(){return navigator.plugins["Shockwave Flash"].description;
},function(){return new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version");})||"0 r0").match(/\d+/g);return{version:parseInt(a[0]||0+"."+a[1],10)||0,build:parseInt(a[2],10)||0};
})();function $exec(b){if(!b){return b;}if(window.execScript){window.execScript(b);}else{var a=document.createElement("script");a.setAttribute("type","text/javascript");
a[(Browser.Engine.webkit&&Browser.Engine.version<420)?"innerText":"text"]=b;document.head.appendChild(a);document.head.removeChild(a);}return b;}Native.UID=1;
var $uid=(Browser.Engine.trident)?function(a){return(a.uid||(a.uid=[Native.UID++]))[0];}:function(a){return a.uid||(a.uid=Native.UID++);};var Window=new Native({name:"Window",legacy:(Browser.Engine.trident)?null:window.Window,initialize:function(a){$uid(a);
if(!a.Element){a.Element=$empty;if(Browser.Engine.webkit){a.document.createElement("iframe");}a.Element.prototype=(Browser.Engine.webkit)?window["[[DOMElement.prototype]]"]:{};
}a.document.window=a;return $extend(a,Window.Prototype);},afterImplement:function(b,a){window[b]=Window.Prototype[b]=a;}});Window.Prototype={$family:{name:"window"}};
new Window(window);var Document=new Native({name:"Document",legacy:(Browser.Engine.trident)?null:window.Document,initialize:function(a){$uid(a);a.head=a.getElementsByTagName("head")[0];
a.html=a.getElementsByTagName("html")[0];if(Browser.Engine.trident&&Browser.Engine.version<=4){$try(function(){a.execCommand("BackgroundImageCache",false,true);
});}if(Browser.Engine.trident){a.window.attachEvent("onunload",function(){a.window.detachEvent("onunload",arguments.callee);a.head=a.html=a.window=null;
});}return $extend(a,Document.Prototype);},afterImplement:function(b,a){document[b]=Document.Prototype[b]=a;}});Document.Prototype={$family:{name:"document"}};
new Document(document);Array.implement({every:function(c,d){for(var b=0,a=this.length;b<a;b++){if(!c.call(d,this[b],b,this)){return false;}}return true;
},filter:function(d,e){var c=[];for(var b=0,a=this.length;b<a;b++){if(d.call(e,this[b],b,this)){c.push(this[b]);}}return c;},clean:function(){return this.filter($defined);
},indexOf:function(c,d){var a=this.length;for(var b=(d<0)?Math.max(0,a+d):d||0;b<a;b++){if(this[b]===c){return b;}}return -1;},map:function(d,e){var c=[];
for(var b=0,a=this.length;b<a;b++){c[b]=d.call(e,this[b],b,this);}return c;},some:function(c,d){for(var b=0,a=this.length;b<a;b++){if(c.call(d,this[b],b,this)){return true;
}}return false;},associate:function(c){var d={},b=Math.min(this.length,c.length);for(var a=0;a<b;a++){d[c[a]]=this[a];}return d;},link:function(c){var a={};
for(var e=0,b=this.length;e<b;e++){for(var d in c){if(c[d](this[e])){a[d]=this[e];delete c[d];break;}}}return a;},contains:function(a,b){return this.indexOf(a,b)!=-1;
},extend:function(c){for(var b=0,a=c.length;b<a;b++){this.push(c[b]);}return this;},getLast:function(){return(this.length)?this[this.length-1]:null;},getRandom:function(){return(this.length)?this[$random(0,this.length-1)]:null;
},include:function(a){if(!this.contains(a)){this.push(a);}return this;},combine:function(c){for(var b=0,a=c.length;b<a;b++){this.include(c[b]);}return this;
},erase:function(b){for(var a=this.length;a--;a){if(this[a]===b){this.splice(a,1);}}return this;},empty:function(){this.length=0;return this;},flatten:function(){var d=[];
for(var b=0,a=this.length;b<a;b++){var c=$type(this[b]);if(!c){continue;}d=d.concat((c=="array"||c=="collection"||c=="arguments")?Array.flatten(this[b]):this[b]);
}return d;},hexToRgb:function(b){if(this.length!=3){return null;}var a=this.map(function(c){if(c.length==1){c+=c;}return c.toInt(16);});return(b)?a:"rgb("+a+")";
},rgbToHex:function(d){if(this.length<3){return null;}if(this.length==4&&this[3]==0&&!d){return"transparent";}var b=[];for(var a=0;a<3;a++){var c=(this[a]-0).toString(16);
b.push((c.length==1)?"0"+c:c);}return(d)?b:"#"+b.join("");}});Function.implement({extend:function(a){for(var b in a){this[b]=a[b];}return this;},create:function(b){var a=this;
b=b||{};return function(d){var c=b.arguments;c=(c!=undefined)?$splat(c):Array.slice(arguments,(b.event)?1:0);if(b.event){c=[d||window.event].extend(c);
}var e=function(){return a.apply(b.bind||null,c);};if(b.delay){return setTimeout(e,b.delay);}if(b.periodical){return setInterval(e,b.periodical);}if(b.attempt){return $try(e);
}return e();};},run:function(a,b){return this.apply(b,$splat(a));},pass:function(a,b){return this.create({bind:b,arguments:a});},bind:function(b,a){return this.create({bind:b,arguments:a});
},bindWithEvent:function(b,a){return this.create({bind:b,arguments:a,event:true});},attempt:function(a,b){return this.create({bind:b,arguments:a,attempt:true})();
},delay:function(b,c,a){return this.create({bind:c,arguments:a,delay:b})();},periodical:function(c,b,a){return this.create({bind:b,arguments:a,periodical:c})();
}});Number.implement({limit:function(b,a){return Math.min(a,Math.max(b,this));},round:function(a){a=Math.pow(10,a||0);return Math.round(this*a)/a;},times:function(b,c){for(var a=0;
a<this;a++){b.call(c,a,this);}},toFloat:function(){return parseFloat(this);},toInt:function(a){return parseInt(this,a||10);}});Number.alias("times","each");
(function(b){var a={};b.each(function(c){if(!Number[c]){a[c]=function(){return Math[c].apply(null,[this].concat($A(arguments)));};}});Number.implement(a);
})(["abs","acos","asin","atan","atan2","ceil","cos","exp","floor","log","max","min","pow","sin","sqrt","tan"]);String.implement({test:function(a,b){return((typeof a=="string")?new RegExp(a,b):a).test(this);
},contains:function(a,b){return(b)?(b+this+b).indexOf(b+a+b)>-1:this.indexOf(a)>-1;},trim:function(){return this.replace(/^\s+|\s+$/g,"");},clean:function(){return this.replace(/\s+/g," ").trim();
},camelCase:function(){return this.replace(/-\D/g,function(a){return a.charAt(1).toUpperCase();});},hyphenate:function(){return this.replace(/[A-Z]/g,function(a){return("-"+a.charAt(0).toLowerCase());
});},capitalize:function(){return this.replace(/\b[a-z]/g,function(a){return a.toUpperCase();});},escapeRegExp:function(){return this.replace(/([-.*+?^${}()|[\]\/\\])/g,"\\$1");
},toInt:function(a){return parseInt(this,a||10);},toFloat:function(){return parseFloat(this);},hexToRgb:function(b){var a=this.match(/^#?(\w{1,2})(\w{1,2})(\w{1,2})$/);
return(a)?a.slice(1).hexToRgb(b):null;},rgbToHex:function(b){var a=this.match(/\d{1,3}/g);return(a)?a.rgbToHex(b):null;},stripScripts:function(b){var a="";
var c=this.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi,function(){a+=arguments[1]+"\n";return"";});if(b===true){$exec(a);}else{if($type(b)=="function"){b(a,c);
}}return c;},substitute:function(a,b){return this.replace(b||(/\\?\{([^{}]+)\}/g),function(d,c){if(d.charAt(0)=="\\"){return d.slice(1);}return(a[c]!=undefined)?a[c]:"";
});}});Hash.implement({has:Object.prototype.hasOwnProperty,keyOf:function(b){for(var a in this){if(this.hasOwnProperty(a)&&this[a]===b){return a;}}return null;
},hasValue:function(a){return(Hash.keyOf(this,a)!==null);},extend:function(a){Hash.each(a||{},function(c,b){Hash.set(this,b,c);},this);return this;},combine:function(a){Hash.each(a||{},function(c,b){Hash.include(this,b,c);
},this);return this;},erase:function(a){if(this.hasOwnProperty(a)){delete this[a];}return this;},get:function(a){return(this.hasOwnProperty(a))?this[a]:null;
},set:function(a,b){if(!this[a]||this.hasOwnProperty(a)){this[a]=b;}return this;},empty:function(){Hash.each(this,function(b,a){delete this[a];},this);
return this;},include:function(a,b){if(this[a]==undefined){this[a]=b;}return this;},map:function(b,c){var a=new Hash;Hash.each(this,function(e,d){a.set(d,b.call(c,e,d,this));
},this);return a;},filter:function(b,c){var a=new Hash;Hash.each(this,function(e,d){if(b.call(c,e,d,this)){a.set(d,e);}},this);return a;},every:function(b,c){for(var a in this){if(this.hasOwnProperty(a)&&!b.call(c,this[a],a)){return false;
}}return true;},some:function(b,c){for(var a in this){if(this.hasOwnProperty(a)&&b.call(c,this[a],a)){return true;}}return false;},getKeys:function(){var a=[];
Hash.each(this,function(c,b){a.push(b);});return a;},getValues:function(){var a=[];Hash.each(this,function(b){a.push(b);});return a;},toQueryString:function(a){var b=[];
Hash.each(this,function(f,e){if(a){e=a+"["+e+"]";}var d;switch($type(f)){case"object":d=Hash.toQueryString(f,e);break;case"array":var c={};f.each(function(h,g){c[g]=h;
});d=Hash.toQueryString(c,e);break;default:d=e+"="+encodeURIComponent(f);}if(f!=undefined){b.push(d);}});return b.join("&");}});Hash.alias({keyOf:"indexOf",hasValue:"contains"});
var Event=new Native({name:"Event",initialize:function(a,f){f=f||window;var k=f.document;a=a||f.event;if(a.$extended){return a;}this.$extended=true;var j=a.type;
var g=a.target||a.srcElement;while(g&&g.nodeType==3){g=g.parentNode;}if(j.test(/key/)){var b=a.which||a.keyCode;var m=Event.Keys.keyOf(b);if(j=="keydown"){var d=b-111;
if(d>0&&d<13){m="f"+d;}}m=m||String.fromCharCode(b).toLowerCase();}else{if(j.match(/(click|mouse|menu)/i)){k=(!k.compatMode||k.compatMode=="CSS1Compat")?k.html:k.body;
var i={x:a.pageX||a.clientX+k.scrollLeft,y:a.pageY||a.clientY+k.scrollTop};var c={x:(a.pageX)?a.pageX-f.pageXOffset:a.clientX,y:(a.pageY)?a.pageY-f.pageYOffset:a.clientY};
if(j.match(/DOMMouseScroll|mousewheel/)){var h=(a.wheelDelta)?a.wheelDelta/120:-(a.detail||0)/3;}var e=(a.which==3)||(a.button==2);var l=null;if(j.match(/over|out/)){switch(j){case"mouseover":l=a.relatedTarget||a.fromElement;
break;case"mouseout":l=a.relatedTarget||a.toElement;}if(!(function(){while(l&&l.nodeType==3){l=l.parentNode;}return true;}).create({attempt:Browser.Engine.gecko})()){l=false;
}}}}return $extend(this,{event:a,type:j,page:i,client:c,rightClick:e,wheel:h,relatedTarget:l,target:g,code:b,key:m,shift:a.shiftKey,control:a.ctrlKey,alt:a.altKey,meta:a.metaKey});
}});Event.Keys=new Hash({enter:13,up:38,down:40,left:37,right:39,esc:27,space:32,backspace:8,tab:9,"delete":46});Event.implement({stop:function(){return this.stopPropagation().preventDefault();
},stopPropagation:function(){if(this.event.stopPropagation){this.event.stopPropagation();}else{this.event.cancelBubble=true;}return this;},preventDefault:function(){if(this.event.preventDefault){this.event.preventDefault();
}else{this.event.returnValue=false;}return this;}});function Class(b){if(b instanceof Function){b={initialize:b};}var a=function(){Object.reset(this);if(a._prototyping){return this;
}this._current=$empty;var c=(this.initialize)?this.initialize.apply(this,arguments):this;delete this._current;delete this.caller;return c;}.extend(this);
a.implement(b);a.constructor=Class;a.prototype.constructor=a;return a;}Function.prototype.protect=function(){this._protected=true;return this;};Object.reset=function(a,c){if(c==null){for(var e in a){Object.reset(a,e);
}return a;}delete a[c];switch($type(a[c])){case"object":var d=function(){};d.prototype=a[c];var b=new d;a[c]=Object.reset(b);break;case"array":a[c]=$unlink(a[c]);
break;}return a;};new Native({name:"Class",initialize:Class}).extend({instantiate:function(b){b._prototyping=true;var a=new b;delete b._prototyping;return a;
},wrap:function(a,b,c){if(c._origin){c=c._origin;}return function(){if(c._protected&&this._current==null){throw new Error('The method "'+b+'" cannot be called.');
}var e=this.caller,f=this._current;this.caller=f;this._current=arguments.callee;var d=c.apply(this,arguments);this._current=f;this.caller=e;return d;}.extend({_owner:a,_origin:c,_name:b});
}});Class.implement({implement:function(a,d){if($type(a)=="object"){for(var e in a){this.implement(e,a[e]);}return this;}var f=Class.Mutators[a];if(f){d=f.call(this,d);
if(d==null){return this;}}var c=this.prototype;switch($type(d)){case"function":if(d._hidden){return this;}c[a]=Class.wrap(this,a,d);break;case"object":var b=c[a];
if($type(b)=="object"){$mixin(b,d);}else{c[a]=$unlink(d);}break;case"array":c[a]=$unlink(d);break;default:c[a]=d;}return this;}});Class.Mutators={Extends:function(a){this.parent=a;
this.prototype=Class.instantiate(a);this.implement("parent",function(){var b=this.caller._name,c=this.caller._owner.parent.prototype[b];if(!c){throw new Error('The method "'+b+'" has no parent.');
}return c.apply(this,arguments);}.protect());},Implements:function(a){$splat(a).each(function(b){if(b instanceof Function){b=Class.instantiate(b);}this.implement(b);
},this);}};var Chain=new Class({$chain:[],chain:function(){this.$chain.extend(Array.flatten(arguments));return this;},callChain:function(){return(this.$chain.length)?this.$chain.shift().apply(this,arguments):false;
},clearChain:function(){this.$chain.empty();return this;}});var Events=new Class({$events:{},addEvent:function(c,b,a){c=Events.removeOn(c);if(b!=$empty){this.$events[c]=this.$events[c]||[];
this.$events[c].include(b);if(a){b.internal=true;}}return this;},addEvents:function(a){for(var b in a){this.addEvent(b,a[b]);}return this;},fireEvent:function(c,b,a){c=Events.removeOn(c);
if(!this.$events||!this.$events[c]){return this;}this.$events[c].each(function(d){d.create({bind:this,delay:a,"arguments":b})();},this);return this;},removeEvent:function(b,a){b=Events.removeOn(b);
if(!this.$events[b]){return this;}if(!a.internal){this.$events[b].erase(a);}return this;},removeEvents:function(c){var d;if($type(c)=="object"){for(d in c){this.removeEvent(d,c[d]);
}return this;}if(c){c=Events.removeOn(c);}for(d in this.$events){if(c&&c!=d){continue;}var b=this.$events[d];for(var a=b.length;a--;a){this.removeEvent(d,b[a]);
}}return this;}});Events.removeOn=function(a){return a.replace(/^on([A-Z])/,function(b,c){return c.toLowerCase();});};var Options=new Class({setOptions:function(){this.options=$merge.run([this.options].extend(arguments));
if(!this.addEvent){return this;}for(var a in this.options){if($type(this.options[a])!="function"||!(/^on[A-Z]/).test(a)){continue;}this.addEvent(a,this.options[a]);
delete this.options[a];}return this;}});var Element=new Native({name:"Element",legacy:window.Element,initialize:function(a,b){var c=Element.Constructors.get(a);
if(c){return c(b);}if(typeof a=="string"){return document.newElement(a,b);}return document.id(a).set(b);},afterImplement:function(a,b){Element.Prototype[a]=b;
if(Array[a]){return;}Elements.implement(a,function(){var c=[],g=true;for(var e=0,d=this.length;e<d;e++){var f=this[e][a].apply(this[e],arguments);c.push(f);
if(g){g=($type(f)=="element");}}return(g)?new Elements(c):c;});}});Element.Prototype={$family:{name:"element"}};Element.Constructors=new Hash;var IFrame=new Native({name:"IFrame",generics:false,initialize:function(){var f=Array.link(arguments,{properties:Object.type,iframe:$defined});
var d=f.properties||{};var c=document.id(f.iframe);var e=d.onload||$empty;delete d.onload;d.id=d.name=$pick(d.id,d.name,c?(c.id||c.name):"IFrame_"+$time());
c=new Element(c||"iframe",d);var b=function(){var g=$try(function(){return c.contentWindow.location.host;});if(!g||g==window.location.host){var h=new Window(c.contentWindow);
new Document(c.contentWindow.document);$extend(h.Element.prototype,Element.Prototype);}e.call(c.contentWindow,c.contentWindow.document);};var a=$try(function(){return c.contentWindow;
});((a&&a.document.body)||window.frames[d.id])?b():c.addListener("load",b);return c;}});var Elements=new Native({initialize:function(f,b){b=$extend({ddup:true,cash:true},b);
f=f||[];if(b.ddup||b.cash){var g={},e=[];for(var c=0,a=f.length;c<a;c++){var d=document.id(f[c],!b.cash);if(b.ddup){if(g[d.uid]){continue;}g[d.uid]=true;
}e.push(d);}f=e;}return(b.cash)?$extend(f,this):f;}});Elements.implement({filter:function(a,b){if(!a){return this;}return new Elements(Array.filter(this,(typeof a=="string")?function(c){return c.match(a);
}:a,b));}});Document.implement({newElement:function(a,b){if(Browser.Engine.trident&&b){["name","type","checked"].each(function(c){if(!b[c]){return;}a+=" "+c+'="'+b[c]+'"';
if(c!="checked"){delete b[c];}});a="<"+a+">";}return document.id(this.createElement(a)).set(b);},newTextNode:function(a){return this.createTextNode(a);
},getDocument:function(){return this;},getWindow:function(){return this.window;},id:(function(){var a={string:function(d,c,b){d=b.getElementById(d);return(d)?a.element(d,c):null;
},element:function(b,e){$uid(b);if(!e&&!b.$family&&!(/^object|embed$/i).test(b.tagName)){var c=Element.Prototype;for(var d in c){b[d]=c[d];}}return b;},object:function(c,d,b){if(c.toElement){return a.element(c.toElement(b),d);
}return null;}};a.textnode=a.whitespace=a.window=a.document=$arguments(0);return function(c,e,d){if(c&&c.$family&&c.uid){return c;}var b=$type(c);return(a[b])?a[b](c,e,d||document):null;
};})()});if(window.$==null){Window.implement({$:function(a,b){return document.id(a,b,this.document);}});}Window.implement({$$:function(a){if(arguments.length==1&&typeof a=="string"){return this.document.getElements(a);
}var f=[];var c=Array.flatten(arguments);for(var d=0,b=c.length;d<b;d++){var e=c[d];switch($type(e)){case"element":f.push(e);break;case"string":f.extend(this.document.getElements(e,true));
}}return new Elements(f);},getDocument:function(){return this.document;},getWindow:function(){return this;}});Native.implement([Element,Document],{getElement:function(a,b){return document.id(this.getElements(a,true)[0]||null,b);
},getElements:function(a,d){a=a.split(",");var c=[];var b=(a.length>1);a.each(function(e){var f=this.getElementsByTagName(e.trim());(b)?c.extend(f):c=f;
},this);return new Elements(c,{ddup:b,cash:!d});}});(function(){var h={},f={};var i={input:"checked",option:"selected",textarea:(Browser.Engine.webkit&&Browser.Engine.version<420)?"innerHTML":"value"};
var c=function(l){return(f[l]||(f[l]={}));};var g=function(n,l){if(!n){return;}var m=n.uid;if(Browser.Engine.trident){if(n.clearAttributes){var q=l&&n.cloneNode(false);
n.clearAttributes();if(q){n.mergeAttributes(q);}}else{if(n.removeEvents){n.removeEvents();}}if((/object/i).test(n.tagName)){for(var o in n){if(typeof n[o]=="function"){n[o]=$empty;
}}Element.dispose(n);}}if(!m){return;}h[m]=f[m]=null;};var d=function(){Hash.each(h,g);if(Browser.Engine.trident){$A(document.getElementsByTagName("object")).each(g);
}if(window.CollectGarbage){CollectGarbage();}h=f=null;};var j=function(n,l,s,m,p,r){var o=n[s||l];var q=[];while(o){if(o.nodeType==1&&(!m||Element.match(o,m))){if(!p){return document.id(o,r);
}q.push(o);}o=o[l];}return(p)?new Elements(q,{ddup:false,cash:!r}):null;};var e={html:"innerHTML","class":"className","for":"htmlFor",defaultValue:"defaultValue",text:(Browser.Engine.trident||(Browser.Engine.webkit&&Browser.Engine.version<420))?"innerText":"textContent"};
var b=["compact","nowrap","ismap","declare","noshade","checked","disabled","readonly","multiple","selected","noresize","defer"];var k=["value","type","defaultValue","accessKey","cellPadding","cellSpacing","colSpan","frameBorder","maxLength","readOnly","rowSpan","tabIndex","useMap"];
b=b.associate(b);Hash.extend(e,b);Hash.extend(e,k.associate(k.map(String.toLowerCase)));var a={before:function(m,l){if(l.parentNode){l.parentNode.insertBefore(m,l);
}},after:function(m,l){if(!l.parentNode){return;}var n=l.nextSibling;(n)?l.parentNode.insertBefore(m,n):l.parentNode.appendChild(m);},bottom:function(m,l){l.appendChild(m);
},top:function(m,l){var n=l.firstChild;(n)?l.insertBefore(m,n):l.appendChild(m);}};a.inside=a.bottom;Hash.each(a,function(l,m){m=m.capitalize();Element.implement("inject"+m,function(n){l(this,document.id(n,true));
return this;});Element.implement("grab"+m,function(n){l(document.id(n,true),this);return this;});});Element.implement({set:function(o,m){switch($type(o)){case"object":for(var n in o){this.set(n,o[n]);
}break;case"string":var l=Element.Properties.get(o);(l&&l.set)?l.set.apply(this,Array.slice(arguments,1)):this.setProperty(o,m);}return this;},get:function(m){var l=Element.Properties.get(m);
return(l&&l.get)?l.get.apply(this,Array.slice(arguments,1)):this.getProperty(m);},erase:function(m){var l=Element.Properties.get(m);(l&&l.erase)?l.erase.apply(this):this.removeProperty(m);
return this;},setProperty:function(m,n){var l=e[m];if(n==undefined){return this.removeProperty(m);}if(l&&b[m]){n=!!n;}(l)?this[l]=n:this.setAttribute(m,""+n);
return this;},setProperties:function(l){for(var m in l){this.setProperty(m,l[m]);}return this;},getProperty:function(m){var l=e[m];var n=(l)?this[l]:this.getAttribute(m,2);
return(b[m])?!!n:(l)?n:n||null;},getProperties:function(){var l=$A(arguments);return l.map(this.getProperty,this).associate(l);},removeProperty:function(m){var l=e[m];
(l)?this[l]=(l&&b[m])?false:"":this.removeAttribute(m);return this;},removeProperties:function(){Array.each(arguments,this.removeProperty,this);return this;
},hasClass:function(l){return this.className.contains(l," ");},addClass:function(l){if(!this.hasClass(l)){this.className=(this.className+" "+l).clean();
}return this;},removeClass:function(l){this.className=this.className.replace(new RegExp("(^|\\s)"+l+"(?:\\s|$)"),"$1");return this;},toggleClass:function(l){return this.hasClass(l)?this.removeClass(l):this.addClass(l);
},adopt:function(){Array.flatten(arguments).each(function(l){l=document.id(l,true);if(l){this.appendChild(l);}},this);return this;},appendText:function(m,l){return this.grab(this.getDocument().newTextNode(m),l);
},grab:function(m,l){a[l||"bottom"](document.id(m,true),this);return this;},inject:function(m,l){a[l||"bottom"](this,document.id(m,true));return this;},replaces:function(l){l=document.id(l,true);
l.parentNode.replaceChild(this,l);return this;},wraps:function(m,l){m=document.id(m,true);return this.replaces(m).grab(m,l);},getPrevious:function(l,m){return j(this,"previousSibling",null,l,false,m);
},getAllPrevious:function(l,m){return j(this,"previousSibling",null,l,true,m);},getNext:function(l,m){return j(this,"nextSibling",null,l,false,m);},getAllNext:function(l,m){return j(this,"nextSibling",null,l,true,m);
},getFirst:function(l,m){return j(this,"nextSibling","firstChild",l,false,m);},getLast:function(l,m){return j(this,"previousSibling","lastChild",l,false,m);
},getParent:function(l,m){return j(this,"parentNode",null,l,false,m);},getParents:function(l,m){return j(this,"parentNode",null,l,true,m);},getSiblings:function(l,m){return this.getParent().getChildren(l,m).erase(this);
},getChildren:function(l,m){return j(this,"nextSibling","firstChild",l,true,m);},getWindow:function(){return this.ownerDocument.window;},getDocument:function(){return this.ownerDocument;
},getElementById:function(o,n){var m=this.ownerDocument.getElementById(o);if(!m){return null;}for(var l=m.parentNode;l!=this;l=l.parentNode){if(!l){return null;
}}return document.id(m,n);},getSelected:function(){return new Elements($A(this.options).filter(function(l){return l.selected;}));},getComputedStyle:function(m){if(this.currentStyle){return this.currentStyle[m.camelCase()];
}var l=this.getDocument().defaultView.getComputedStyle(this,null);return(l)?l.getPropertyValue([m.hyphenate()]):null;},toQueryString:function(){var l=[];
this.getElements("input, select, textarea",true).each(function(m){if(!m.name||m.disabled||m.type=="submit"||m.type=="reset"||m.type=="file"){return;}var n=(m.tagName.toLowerCase()=="select")?Element.getSelected(m).map(function(o){return o.value;
}):((m.type=="radio"||m.type=="checkbox")&&!m.checked)?null:m.value;$splat(n).each(function(o){if(typeof o!="undefined"){l.push(m.name+"="+encodeURIComponent(o));
}});});return l.join("&");},clone:function(o,l){o=o!==false;var r=this.cloneNode(o);var n=function(v,u){if(!l){v.removeAttribute("id");}if(Browser.Engine.trident){v.clearAttributes();
v.mergeAttributes(u);v.removeAttribute("uid");if(v.options){var w=v.options,s=u.options;for(var t=w.length;t--;){w[t].selected=s[t].selected;}}}var x=i[u.tagName.toLowerCase()];
if(x&&u[x]){v[x]=u[x];}};if(o){var p=r.getElementsByTagName("*"),q=this.getElementsByTagName("*");for(var m=p.length;m--;){n(p[m],q[m]);}}n(r,this);return document.id(r);
},destroy:function(){Element.empty(this);Element.dispose(this);g(this,true);return null;},empty:function(){$A(this.childNodes).each(function(l){Element.destroy(l);
});return this;},dispose:function(){return(this.parentNode)?this.parentNode.removeChild(this):this;},hasChild:function(l){l=document.id(l,true);if(!l){return false;
}if(Browser.Engine.webkit&&Browser.Engine.version<420){return $A(this.getElementsByTagName(l.tagName)).contains(l);}return(this.contains)?(this!=l&&this.contains(l)):!!(this.compareDocumentPosition(l)&16);
},match:function(l){return(!l||(l==this)||(Element.get(this,"tag")==l));}});Native.implement([Element,Window,Document],{addListener:function(o,n){if(o=="unload"){var l=n,m=this;
n=function(){m.removeListener("unload",n);l();};}else{h[this.uid]=this;}if(this.addEventListener){this.addEventListener(o,n,false);}else{this.attachEvent("on"+o,n);
}return this;},removeListener:function(m,l){if(this.removeEventListener){this.removeEventListener(m,l,false);}else{this.detachEvent("on"+m,l);}return this;
},retrieve:function(m,l){var o=c(this.uid),n=o[m];if(l!=undefined&&n==undefined){n=o[m]=l;}return $pick(n);},store:function(m,l){var n=c(this.uid);n[m]=l;
return this;},eliminate:function(l){var m=c(this.uid);delete m[l];return this;}});window.addListener("unload",d);})();Element.Properties=new Hash;Element.Properties.style={set:function(a){this.style.cssText=a;
},get:function(){return this.style.cssText;},erase:function(){this.style.cssText="";}};Element.Properties.tag={get:function(){return this.tagName.toLowerCase();
}};Element.Properties.html=(function(){var c=document.createElement("div");var a={table:[1,"<table>","</table>"],select:[1,"<select>","</select>"],tbody:[2,"<table><tbody>","</tbody></table>"],tr:[3,"<table><tbody><tr>","</tr></tbody></table>"]};
a.thead=a.tfoot=a.tbody;var b={set:function(){var e=Array.flatten(arguments).join("");var f=Browser.Engine.trident&&a[this.get("tag")];if(f){var g=c;g.innerHTML=f[1]+e+f[2];
for(var d=f[0];d--;){g=g.firstChild;}this.empty().adopt(g.childNodes);}else{this.innerHTML=e;}}};b.erase=b.set;return b;})();if(Browser.Engine.webkit&&Browser.Engine.version<420){Element.Properties.text={get:function(){if(this.innerText){return this.innerText;
}var a=this.ownerDocument.newElement("div",{html:this.innerHTML}).inject(this.ownerDocument.body);var b=a.innerText;a.destroy();return b;}};}Element.Properties.events={set:function(a){this.addEvents(a);
}};Native.implement([Element,Window,Document],{addEvent:function(e,g){var h=this.retrieve("events",{});h[e]=h[e]||{keys:[],values:[]};if(h[e].keys.contains(g)){return this;
}h[e].keys.push(g);var f=e,a=Element.Events.get(e),c=g,i=this;if(a){if(a.onAdd){a.onAdd.call(this,g);}if(a.condition){c=function(j){if(a.condition.call(this,j)){return g.call(this,j);
}return true;};}f=a.base||f;}var d=function(){return g.call(i);};var b=Element.NativeEvents[f];if(b){if(b==2){d=function(j){j=new Event(j,i.getWindow());
if(c.call(i,j)===false){j.stop();}};}this.addListener(f,d);}h[e].values.push(d);return this;},removeEvent:function(c,b){var a=this.retrieve("events");if(!a||!a[c]){return this;
}var f=a[c].keys.indexOf(b);if(f==-1){return this;}a[c].keys.splice(f,1);var e=a[c].values.splice(f,1)[0];var d=Element.Events.get(c);if(d){if(d.onRemove){d.onRemove.call(this,b);
}c=d.base||c;}return(Element.NativeEvents[c])?this.removeListener(c,e):this;},addEvents:function(a){for(var b in a){this.addEvent(b,a[b]);}return this;
},removeEvents:function(a){var c;if($type(a)=="object"){for(c in a){this.removeEvent(c,a[c]);}return this;}var b=this.retrieve("events");if(!b){return this;
}if(!a){for(c in b){this.removeEvents(c);}this.eliminate("events");}else{if(b[a]){while(b[a].keys[0]){this.removeEvent(a,b[a].keys[0]);}b[a]=null;}}return this;
},fireEvent:function(d,b,a){var c=this.retrieve("events");if(!c||!c[d]){return this;}c[d].keys.each(function(e){e.create({bind:this,delay:a,"arguments":b})();
},this);return this;},cloneEvents:function(d,a){d=document.id(d);var c=d.retrieve("events");if(!c){return this;}if(!a){for(var b in c){this.cloneEvents(d,b);
}}else{if(c[a]){c[a].keys.each(function(e){this.addEvent(a,e);},this);}}return this;}});Element.NativeEvents={click:2,dblclick:2,mouseup:2,mousedown:2,contextmenu:2,mousewheel:2,DOMMouseScroll:2,mouseover:2,mouseout:2,mousemove:2,selectstart:2,selectend:2,keydown:2,keypress:2,keyup:2,focus:2,blur:2,change:2,reset:2,select:2,submit:2,load:1,unload:1,beforeunload:2,resize:1,move:1,DOMContentLoaded:1,readystatechange:1,error:1,abort:1,scroll:1};
(function(){var a=function(b){var c=b.relatedTarget;if(c==undefined){return true;}if(c===false){return false;}return($type(this)!="document"&&c!=this&&c.prefix!="xul"&&!this.hasChild(c));
};Element.Events=new Hash({mouseenter:{base:"mouseover",condition:a},mouseleave:{base:"mouseout",condition:a},mousewheel:{base:(Browser.Engine.gecko)?"DOMMouseScroll":"mousewheel"}});
})();Element.Properties.styles={set:function(a){this.setStyles(a);}};Element.Properties.opacity={set:function(a,b){if(!b){if(a==0){if(this.style.visibility!="hidden"){this.style.visibility="hidden";
}}else{if(this.style.visibility!="visible"){this.style.visibility="visible";}}}if(!this.currentStyle||!this.currentStyle.hasLayout){this.style.zoom=1;}if(Browser.Engine.trident){this.style.filter=(a==1)?"":"alpha(opacity="+a*100+")";
}this.style.opacity=a;this.store("opacity",a);},get:function(){return this.retrieve("opacity",1);}};Element.implement({setOpacity:function(a){return this.set("opacity",a,true);
},getOpacity:function(){return this.get("opacity");},setStyle:function(b,a){switch(b){case"opacity":return this.set("opacity",parseFloat(a));case"float":b=(Browser.Engine.trident)?"styleFloat":"cssFloat";
}b=b.camelCase();if($type(a)!="string"){var c=(Element.Styles.get(b)||"@").split(" ");a=$splat(a).map(function(e,d){if(!c[d]){return"";}return($type(e)=="number")?c[d].replace("@",Math.round(e)):e;
}).join(" ");}else{if(a==String(Number(a))){a=Math.round(a);}}this.style[b]=a;return this;},getStyle:function(g){switch(g){case"opacity":return this.get("opacity");
case"float":g=(Browser.Engine.trident)?"styleFloat":"cssFloat";}g=g.camelCase();var a=this.style[g];if(!$chk(a)){a=[];for(var f in Element.ShortStyles){if(g!=f){continue;
}for(var e in Element.ShortStyles[f]){a.push(this.getStyle(e));}return a.join(" ");}a=this.getComputedStyle(g);}if(a){a=String(a);var c=a.match(/rgba?\([\d\s,]+\)/);
if(c){a=a.replace(c[0],c[0].rgbToHex());}}if(Browser.Engine.presto||(Browser.Engine.trident&&!$chk(parseInt(a,10)))){if(g.test(/^(height|width)$/)){var b=(g=="width")?["left","right"]:["top","bottom"],d=0;
b.each(function(h){d+=this.getStyle("border-"+h+"-width").toInt()+this.getStyle("padding-"+h).toInt();},this);return this["offset"+g.capitalize()]-d+"px";
}if((Browser.Engine.presto)&&String(a).test("px")){return a;}if(g.test(/(border(.+)Width|margin|padding)/)){return"0px";}}return a;},setStyles:function(b){for(var a in b){this.setStyle(a,b[a]);
}return this;},getStyles:function(){var a={};Array.flatten(arguments).each(function(b){a[b]=this.getStyle(b);},this);return a;}});Element.Styles=new Hash({left:"@px",top:"@px",bottom:"@px",right:"@px",width:"@px",height:"@px",maxWidth:"@px",maxHeight:"@px",minWidth:"@px",minHeight:"@px",backgroundColor:"rgb(@, @, @)",backgroundPosition:"@px @px",color:"rgb(@, @, @)",fontSize:"@px",letterSpacing:"@px",lineHeight:"@px",clip:"rect(@px @px @px @px)",margin:"@px @px @px @px",padding:"@px @px @px @px",border:"@px @ rgb(@, @, @) @px @ rgb(@, @, @) @px @ rgb(@, @, @)",borderWidth:"@px @px @px @px",borderStyle:"@ @ @ @",borderColor:"rgb(@, @, @) rgb(@, @, @) rgb(@, @, @) rgb(@, @, @)",zIndex:"@",zoom:"@",fontWeight:"@",textIndent:"@px",opacity:"@"});
Element.ShortStyles={margin:{},padding:{},border:{},borderWidth:{},borderStyle:{},borderColor:{}};["Top","Right","Bottom","Left"].each(function(g){var f=Element.ShortStyles;
var b=Element.Styles;["margin","padding"].each(function(h){var i=h+g;f[h][i]=b[i]="@px";});var e="border"+g;f.border[e]=b[e]="@px @ rgb(@, @, @)";var d=e+"Width",a=e+"Style",c=e+"Color";
f[e]={};f.borderWidth[d]=f[e][d]=b[d]="@px";f.borderStyle[a]=f[e][a]=b[a]="@";f.borderColor[c]=f[e][c]=b[c]="rgb(@, @, @)";});(function(){Element.implement({scrollTo:function(h,i){if(b(this)){this.getWindow().scrollTo(h,i);
}else{this.scrollLeft=h;this.scrollTop=i;}return this;},getSize:function(){if(b(this)){return this.getWindow().getSize();}return{x:this.offsetWidth,y:this.offsetHeight};
},getScrollSize:function(){if(b(this)){return this.getWindow().getScrollSize();}return{x:this.scrollWidth,y:this.scrollHeight};},getScroll:function(){if(b(this)){return this.getWindow().getScroll();
}return{x:this.scrollLeft,y:this.scrollTop};},getScrolls:function(){var i=this,h={x:0,y:0};while(i&&!b(i)){h.x+=i.scrollLeft;h.y+=i.scrollTop;i=i.parentNode;
}return h;},getOffsetParent:function(){var h=this;if(b(h)){return null;}if(!Browser.Engine.trident){return h.offsetParent;}while((h=h.parentNode)&&!b(h)){if(d(h,"position")!="static"){return h;
}}return null;},getOffsets:function(){if(this.getBoundingClientRect){var m=this.getBoundingClientRect(),k=document.id(this.getDocument().documentElement),i=k.getScroll(),n=(d(this,"position")=="fixed");
return{x:parseInt(m.left,10)+((n)?0:i.x)-k.clientLeft,y:parseInt(m.top,10)+((n)?0:i.y)-k.clientTop};}var j=this,h={x:0,y:0};if(b(this)){return h;}while(j&&!b(j)){h.x+=j.offsetLeft;
h.y+=j.offsetTop;if(Browser.Engine.gecko){if(!f(j)){h.x+=c(j);h.y+=g(j);}var l=j.parentNode;if(l&&d(l,"overflow")!="visible"){h.x+=c(l);h.y+=g(l);}}else{if(j!=this&&Browser.Engine.webkit){h.x+=c(j);
h.y+=g(j);}}j=j.offsetParent;}if(Browser.Engine.gecko&&!f(this)){h.x-=c(this);h.y-=g(this);}return h;},getPosition:function(k){if(b(this)){return{x:0,y:0};
}var l=this.getOffsets(),i=this.getScrolls();var h={x:l.x-i.x,y:l.y-i.y};var j=(k&&(k=document.id(k)))?k.getPosition():{x:0,y:0};return{x:h.x-j.x,y:h.y-j.y};
},getCoordinates:function(j){if(b(this)){return this.getWindow().getCoordinates();}var h=this.getPosition(j),i=this.getSize();var k={left:h.x,top:h.y,width:i.x,height:i.y};
k.right=k.left+k.width;k.bottom=k.top+k.height;return k;},computePosition:function(h){return{left:h.x-e(this,"margin-left"),top:h.y-e(this,"margin-top")};
},setPosition:function(h){return this.setStyles(this.computePosition(h));}});Native.implement([Document,Window],{getSize:function(){if(Browser.Engine.presto||Browser.Engine.webkit){var i=this.getWindow();
return{x:i.innerWidth,y:i.innerHeight};}var h=a(this);return{x:h.clientWidth,y:h.clientHeight};},getScroll:function(){var i=this.getWindow(),h=a(this);
return{x:i.pageXOffset||h.scrollLeft,y:i.pageYOffset||h.scrollTop};},getScrollSize:function(){var i=a(this),h=this.getSize();return{x:Math.max(i.scrollWidth,h.x),y:Math.max(i.scrollHeight,h.y)};
},getPosition:function(){return{x:0,y:0};},getCoordinates:function(){var h=this.getSize();return{top:0,left:0,bottom:h.y,right:h.x,height:h.y,width:h.x};
}});var d=Element.getComputedStyle;function e(h,i){return d(h,i).toInt()||0;}function f(h){return d(h,"-moz-box-sizing")=="border-box";}function g(h){return e(h,"border-top-width");
}function c(h){return e(h,"border-left-width");}function b(h){return(/^(?:body|html)$/i).test(h.tagName);}function a(h){var i=h.getDocument();return(!i.compatMode||i.compatMode=="CSS1Compat")?i.html:i.body;
}})();Element.alias("setPosition","position");Native.implement([Window,Document,Element],{getHeight:function(){return this.getSize().y;},getWidth:function(){return this.getSize().x;
},getScrollTop:function(){return this.getScroll().y;},getScrollLeft:function(){return this.getScroll().x;},getScrollHeight:function(){return this.getScrollSize().y;
},getScrollWidth:function(){return this.getScrollSize().x;},getTop:function(){return this.getPosition().y;},getLeft:function(){return this.getPosition().x;
}});Native.implement([Document,Element],{getElements:function(h,g){h=h.split(",");var c,e={};for(var d=0,b=h.length;d<b;d++){var a=h[d],f=Selectors.Utils.search(this,a,e);
if(d!=0&&f.item){f=$A(f);}c=(d==0)?f:(c.item)?$A(c).concat(f):c.concat(f);}return new Elements(c,{ddup:(h.length>1),cash:!g});}});Element.implement({match:function(b){if(!b||(b==this)){return true;
}var d=Selectors.Utils.parseTagAndID(b);var a=d[0],e=d[1];if(!Selectors.Filters.byID(this,e)||!Selectors.Filters.byTag(this,a)){return false;}var c=Selectors.Utils.parseSelector(b);
return(c)?Selectors.Utils.filter(this,c,{}):true;}});var Selectors={Cache:{nth:{},parsed:{}}};Selectors.RegExps={id:(/#([\w-]+)/),tag:(/^(\w+|\*)/),quick:(/^(\w+|\*)$/),splitter:(/\s*([+>~\s])\s*([a-zA-Z#.*:\[])/g),combined:(/\.([\w-]+)|\[(\w+)(?:([!*^$~|]?=)(["']?)([^\4]*?)\4)?\]|:([\w-]+)(?:\(["']?(.*?)?["']?\)|$)/g)};
Selectors.Utils={chk:function(b,c){if(!c){return true;}var a=$uid(b);if(!c[a]){return c[a]=true;}return false;},parseNthArgument:function(h){if(Selectors.Cache.nth[h]){return Selectors.Cache.nth[h];
}var e=h.match(/^([+-]?\d*)?([a-z]+)?([+-]?\d*)?$/);if(!e){return false;}var g=parseInt(e[1],10);var d=(g||g===0)?g:1;var f=e[2]||false;var c=parseInt(e[3],10)||0;
if(d!=0){c--;while(c<1){c+=d;}while(c>=d){c-=d;}}else{d=c;f="index";}switch(f){case"n":e={a:d,b:c,special:"n"};break;case"odd":e={a:2,b:0,special:"n"};
break;case"even":e={a:2,b:1,special:"n"};break;case"first":e={a:0,special:"index"};break;case"last":e={special:"last-child"};break;case"only":e={special:"only-child"};
break;default:e={a:(d-1),special:"index"};}return Selectors.Cache.nth[h]=e;},parseSelector:function(e){if(Selectors.Cache.parsed[e]){return Selectors.Cache.parsed[e];
}var d,h={classes:[],pseudos:[],attributes:[]};while((d=Selectors.RegExps.combined.exec(e))){var i=d[1],g=d[2],f=d[3],b=d[5],c=d[6],j=d[7];if(i){h.classes.push(i);
}else{if(c){var a=Selectors.Pseudo.get(c);if(a){h.pseudos.push({parser:a,argument:j});}else{h.attributes.push({name:c,operator:"=",value:j});}}else{if(g){h.attributes.push({name:g,operator:f,value:b});
}}}}if(!h.classes.length){delete h.classes;}if(!h.attributes.length){delete h.attributes;}if(!h.pseudos.length){delete h.pseudos;}if(!h.classes&&!h.attributes&&!h.pseudos){h=null;
}return Selectors.Cache.parsed[e]=h;},parseTagAndID:function(b){var a=b.match(Selectors.RegExps.tag);var c=b.match(Selectors.RegExps.id);return[(a)?a[1]:"*",(c)?c[1]:false];
},filter:function(f,c,e){var d;if(c.classes){for(d=c.classes.length;d--;d){var g=c.classes[d];if(!Selectors.Filters.byClass(f,g)){return false;}}}if(c.attributes){for(d=c.attributes.length;
d--;d){var b=c.attributes[d];if(!Selectors.Filters.byAttribute(f,b.name,b.operator,b.value)){return false;}}}if(c.pseudos){for(d=c.pseudos.length;d--;d){var a=c.pseudos[d];
if(!Selectors.Filters.byPseudo(f,a.parser,a.argument,e)){return false;}}}return true;},getByTagAndID:function(b,a,d){if(d){var c=(b.getElementById)?b.getElementById(d,true):Element.getElementById(b,d,true);
return(c&&Selectors.Filters.byTag(c,a))?[c]:[];}else{return b.getElementsByTagName(a);}},search:function(o,h,t){var b=[];var c=h.trim().replace(Selectors.RegExps.splitter,function(k,j,i){b.push(j);
return":)"+i;}).split(":)");var p,e,A;for(var z=0,v=c.length;z<v;z++){var y=c[z];if(z==0&&Selectors.RegExps.quick.test(y)){p=o.getElementsByTagName(y);
continue;}var a=b[z-1];var q=Selectors.Utils.parseTagAndID(y);var B=q[0],r=q[1];if(z==0){p=Selectors.Utils.getByTagAndID(o,B,r);}else{var d={},g=[];for(var x=0,w=p.length;
x<w;x++){g=Selectors.Getters[a](g,p[x],B,r,d);}p=g;}var f=Selectors.Utils.parseSelector(y);if(f){e=[];for(var u=0,s=p.length;u<s;u++){A=p[u];if(Selectors.Utils.filter(A,f,t)){e.push(A);
}}p=e;}}return p;}};Selectors.Getters={" ":function(h,g,j,a,e){var d=Selectors.Utils.getByTagAndID(g,j,a);for(var c=0,b=d.length;c<b;c++){var f=d[c];if(Selectors.Utils.chk(f,e)){h.push(f);
}}return h;},">":function(h,g,j,a,f){var c=Selectors.Utils.getByTagAndID(g,j,a);for(var e=0,d=c.length;e<d;e++){var b=c[e];if(b.parentNode==g&&Selectors.Utils.chk(b,f)){h.push(b);
}}return h;},"+":function(c,b,a,e,d){while((b=b.nextSibling)){if(b.nodeType==1){if(Selectors.Utils.chk(b,d)&&Selectors.Filters.byTag(b,a)&&Selectors.Filters.byID(b,e)){c.push(b);
}break;}}return c;},"~":function(c,b,a,e,d){while((b=b.nextSibling)){if(b.nodeType==1){if(!Selectors.Utils.chk(b,d)){break;}if(Selectors.Filters.byTag(b,a)&&Selectors.Filters.byID(b,e)){c.push(b);
}}}return c;}};Selectors.Filters={byTag:function(b,a){return(a=="*"||(b.tagName&&b.tagName.toLowerCase()==a));},byID:function(a,b){return(!b||(a.id&&a.id==b));
},byClass:function(b,a){return(b.className&&b.className.contains(a," "));},byPseudo:function(a,d,c,b){return d.call(a,c,b);},byAttribute:function(c,d,b,e){var a=Element.prototype.getProperty.call(c,d);
if(!a){return(b=="!=");}if(!b||e==undefined){return true;}switch(b){case"=":return(a==e);case"*=":return(a.contains(e));case"^=":return(a.substr(0,e.length)==e);
case"$=":return(a.substr(a.length-e.length)==e);case"!=":return(a!=e);case"~=":return a.contains(e," ");case"|=":return a.contains(e,"-");}return false;
}};Selectors.Pseudo=new Hash({checked:function(){return this.checked;},empty:function(){return !(this.innerText||this.textContent||"").length;},not:function(a){return !Element.match(this,a);
},contains:function(a){return(this.innerText||this.textContent||"").contains(a);},"first-child":function(){return Selectors.Pseudo.index.call(this,0);},"last-child":function(){var a=this;
while((a=a.nextSibling)){if(a.nodeType==1){return false;}}return true;},"only-child":function(){var b=this;while((b=b.previousSibling)){if(b.nodeType==1){return false;
}}var a=this;while((a=a.nextSibling)){if(a.nodeType==1){return false;}}return true;},"nth-child":function(g,e){g=(g==undefined)?"n":g;var c=Selectors.Utils.parseNthArgument(g);
if(c.special!="n"){return Selectors.Pseudo[c.special].call(this,c.a,e);}var f=0;e.positions=e.positions||{};var d=$uid(this);if(!e.positions[d]){var b=this;
while((b=b.previousSibling)){if(b.nodeType!=1){continue;}f++;var a=e.positions[$uid(b)];if(a!=undefined){f=a+f;break;}}e.positions[d]=f;}return(e.positions[d]%c.a==c.b);
},index:function(a){var b=this,c=0;while((b=b.previousSibling)){if(b.nodeType==1&&++c>a){return false;}}return(c==a);},even:function(b,a){return Selectors.Pseudo["nth-child"].call(this,"2n+1",a);
},odd:function(b,a){return Selectors.Pseudo["nth-child"].call(this,"2n",a);},selected:function(){return this.selected;},enabled:function(){return(this.disabled===false);
}});Element.Events.domready={onAdd:function(a){if(Browser.loaded){a.call(this);}}};(function(){var b=function(){if(Browser.loaded){return;}Browser.loaded=true;
window.fireEvent("domready");document.fireEvent("domready");};if(Browser.Engine.trident){var a=document.createElement("div");(function(){($try(function(){a.doScroll();
return document.id(a).inject(document.body).set("html","temp").dispose();}))?b():arguments.callee.delay(50);})();}else{if(Browser.Engine.webkit&&Browser.Engine.version<525){(function(){(["loaded","complete"].contains(document.readyState))?b():arguments.callee.delay(50);
})();}else{window.addEvent("load",b);document.addEvent("DOMContentLoaded",b);}}})();var JSON=new Hash({$specialChars:{"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},$replaceChars:function(a){return JSON.$specialChars[a]||"\\u00"+Math.floor(a.charCodeAt()/16).toString(16)+(a.charCodeAt()%16).toString(16);
},encode:function(b){switch($type(b)){case"string":return'"'+b.replace(/[\x00-\x1f\\"]/g,JSON.$replaceChars)+'"';case"array":return"["+String(b.map(JSON.encode).clean())+"]";
case"object":case"hash":var a=[];Hash.each(b,function(e,d){var c=JSON.encode(e);if(c){a.push(JSON.encode(d)+":"+c);}});return"{"+a+"}";case"number":case"boolean":return String(b);
case false:return"null";}return null;},decode:function(string,secure){if($type(string)!="string"||!string.length){return null;}if(secure&&!(/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(string.replace(/\\./g,"@").replace(/"[^"\\\n\r]*"/g,""))){return null;
}return eval("("+string+")");}});Native.implement([Hash,Array,String,Number],{toJSON:function(){return JSON.encode(this);}});var Cookie=new Class({Implements:Options,options:{path:false,domain:false,duration:false,secure:false,document:document},initialize:function(b,a){this.key=b;
this.setOptions(a);},write:function(b){b=encodeURIComponent(b);if(this.options.domain){b+="; domain="+this.options.domain;}if(this.options.path){b+="; path="+this.options.path;
}if(this.options.duration){var a=new Date();a.setTime(a.getTime()+this.options.duration*24*60*60*1000);b+="; expires="+a.toGMTString();}if(this.options.secure){b+="; secure";
}this.options.document.cookie=this.key+"="+b;return this;},read:function(){var a=this.options.document.cookie.match("(?:^|;)\\s*"+this.key.escapeRegExp()+"=([^;]*)");
return(a)?decodeURIComponent(a[1]):null;},dispose:function(){new Cookie(this.key,$merge(this.options,{duration:-1})).write("");return this;}});Cookie.write=function(b,c,a){return new Cookie(b,a).write(c);
};Cookie.read=function(a){return new Cookie(a).read();};Cookie.dispose=function(b,a){return new Cookie(b,a).dispose();};var Swiff=new Class({Implements:[Options],options:{id:null,height:1,width:1,container:null,properties:{},params:{quality:"high",allowScriptAccess:"always",wMode:"transparent",swLiveConnect:true},callBacks:{},vars:{}},toElement:function(){return this.object;
},initialize:function(l,m){this.instance="Swiff_"+$time();this.setOptions(m);m=this.options;var b=this.id=m.id||this.instance;var a=document.id(m.container);
Swiff.CallBacks[this.instance]={};var e=m.params,g=m.vars,f=m.callBacks;var h=$extend({height:m.height,width:m.width},m.properties);var k=this;for(var d in f){Swiff.CallBacks[this.instance][d]=(function(n){return function(){return n.apply(k.object,arguments);
};})(f[d]);g[d]="Swiff.CallBacks."+this.instance+"."+d;}e.flashVars=Hash.toQueryString(g);if(Browser.Engine.trident){h.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";
e.movie=l;}else{h.type="application/x-shockwave-flash";h.data=l;}var j='<object id="'+b+'"';for(var i in h){j+=" "+i+'="'+h[i]+'"';}j+=">";for(var c in e){if(e[c]){j+='<param name="'+c+'" value="'+e[c]+'" />';
}}j+="</object>";this.object=((a)?a.empty():new Element("div")).set("html",j).firstChild;},replaces:function(a){a=document.id(a,true);a.parentNode.replaceChild(this.toElement(),a);
return this;},inject:function(a){document.id(a,true).appendChild(this.toElement());return this;},remote:function(){return Swiff.remote.apply(Swiff,[this.toElement()].extend(arguments));
}});Swiff.CallBacks={};Swiff.remote=function(obj,fn){var rs=obj.CallFunction('<invoke name="'+fn+'" returntype="javascript">'+__flash__argumentsToXML(arguments,2)+"</invoke>");
return eval(rs);};var Fx=new Class({Implements:[Chain,Events,Options],options:{fps:50,unit:false,duration:500,link:"ignore"},initialize:function(a){this.subject=this.subject||this;
this.setOptions(a);this.options.duration=Fx.Durations[this.options.duration]||this.options.duration.toInt();var b=this.options.wait;if(b===false){this.options.link="cancel";
}},getTransition:function(){return function(a){return -(Math.cos(Math.PI*a)-1)/2;};},step:function(){var a=$time();if(a<this.time+this.options.duration){var b=this.transition((a-this.time)/this.options.duration);
this.set(this.compute(this.from,this.to,b));}else{this.set(this.compute(this.from,this.to,1));this.complete();}},set:function(a){return a;},compute:function(c,b,a){return Fx.compute(c,b,a);
},check:function(){if(!this.timer){return true;}switch(this.options.link){case"cancel":this.cancel();return true;case"chain":this.chain(this.caller.bind(this,arguments));
return false;}return false;},start:function(b,a){if(!this.check(b,a)){return this;}this.from=b;this.to=a;this.time=0;this.transition=this.getTransition();
this.startTimer();this.onStart();return this;},complete:function(){if(this.stopTimer()){this.onComplete();}return this;},cancel:function(){if(this.stopTimer()){this.onCancel();
}return this;},onStart:function(){this.fireEvent("start",this.subject);},onComplete:function(){this.fireEvent("complete",this.subject);if(!this.callChain()){this.fireEvent("chainComplete",this.subject);
}},onCancel:function(){this.fireEvent("cancel",this.subject).clearChain();},pause:function(){this.stopTimer();return this;},resume:function(){this.startTimer();
return this;},stopTimer:function(){if(!this.timer){return false;}this.time=$time()-this.time;this.timer=$clear(this.timer);return true;},startTimer:function(){if(this.timer){return false;
}this.time=$time()-this.time;this.timer=this.step.periodical(Math.round(1000/this.options.fps),this);return true;}});Fx.compute=function(c,b,a){return(b-c)*a+c;
};Fx.Durations={"short":250,normal:500,"long":1000};Fx.CSS=new Class({Extends:Fx,prepare:function(d,e,b){b=$splat(b);var c=b[1];if(!$chk(c)){b[1]=b[0];
b[0]=d.getStyle(e);}var a=b.map(this.parse);return{from:a[0],to:a[1]};},parse:function(a){a=$lambda(a)();a=(typeof a=="string")?a.split(" "):$splat(a);
return a.map(function(c){c=String(c);var b=false;Fx.CSS.Parsers.each(function(f,e){if(b){return;}var d=f.parse(c);if($chk(d)){b={value:d,parser:f};}});
b=b||{value:c,parser:Fx.CSS.Parsers.String};return b;});},compute:function(d,c,b){var a=[];(Math.min(d.length,c.length)).times(function(e){a.push({value:d[e].parser.compute(d[e].value,c[e].value,b),parser:d[e].parser});
});a.$family={name:"fx:css:value"};return a;},serve:function(c,b){if($type(c)!="fx:css:value"){c=this.parse(c);}var a=[];c.each(function(d){a=a.concat(d.parser.serve(d.value,b));
});return a;},render:function(a,d,c,b){a.setStyle(d,this.serve(c,b));},search:function(a){if(Fx.CSS.Cache[a]){return Fx.CSS.Cache[a];}var b={};Array.each(document.styleSheets,function(e,d){var c=e.href;
if(c&&c.contains("://")&&!c.contains(document.domain)){return;}var f=e.rules||e.cssRules;Array.each(f,function(j,g){if(!j.style){return;}var h=(j.selectorText)?j.selectorText.replace(/^\w+/,function(i){return i.toLowerCase();
}):null;if(!h||!h.test("^"+a+"$")){return;}Element.Styles.each(function(k,i){if(!j.style[i]||Element.ShortStyles[i]){return;}k=String(j.style[i]);b[i]=(k.test(/^rgb/))?k.rgbToHex():k;
});});});return Fx.CSS.Cache[a]=b;}});Fx.CSS.Cache={};Fx.CSS.Parsers=new Hash({Color:{parse:function(a){if(a.match(/^#[0-9a-f]{3,6}$/i)){return a.hexToRgb(true);
}return((a=a.match(/(\d+),\s*(\d+),\s*(\d+)/)))?[a[1],a[2],a[3]]:false;},compute:function(c,b,a){return c.map(function(e,d){return Math.round(Fx.compute(c[d],b[d],a));
});},serve:function(a){return a.map(Number);}},Number:{parse:parseFloat,compute:Fx.compute,serve:function(b,a){return(a)?b+a:b;}},String:{parse:$lambda(false),compute:$arguments(1),serve:$arguments(0)}});
Fx.Tween=new Class({Extends:Fx.CSS,initialize:function(b,a){this.element=this.subject=document.id(b);this.parent(a);},set:function(b,a){if(arguments.length==1){a=b;
b=this.property||this.options.property;}this.render(this.element,b,a,this.options.unit);return this;},start:function(c,e,d){if(!this.check(c,e,d)){return this;
}var b=Array.flatten(arguments);this.property=this.options.property||b.shift();var a=this.prepare(this.element,this.property,b);return this.parent(a.from,a.to);
}});Element.Properties.tween={set:function(a){var b=this.retrieve("tween");if(b){b.cancel();}return this.eliminate("tween").store("tween:options",$extend({link:"cancel"},a));
},get:function(a){if(a||!this.retrieve("tween")){if(a||!this.retrieve("tween:options")){this.set("tween",a);}this.store("tween",new Fx.Tween(this,this.retrieve("tween:options")));
}return this.retrieve("tween");}};Element.implement({tween:function(a,c,b){this.get("tween").start(arguments);return this;},fade:function(c){var e=this.get("tween"),d="opacity",a;
c=$pick(c,"toggle");switch(c){case"in":e.start(d,1);break;case"out":e.start(d,0);break;case"show":e.set(d,1);break;case"hide":e.set(d,0);break;case"toggle":var b=this.retrieve("fade:flag",this.get("opacity")==1);
e.start(d,(b)?0:1);this.store("fade:flag",!b);a=true;break;default:e.start(d,arguments);}if(!a){this.eliminate("fade:flag");}return this;},highlight:function(c,a){if(!a){a=this.retrieve("highlight:original",this.getStyle("background-color"));
a=(a=="transparent")?"#fff":a;}var b=this.get("tween");b.start("background-color",c||"#ffff88",a).chain(function(){this.setStyle("background-color",this.retrieve("highlight:original"));
b.callChain();}.bind(this));return this;}});Fx.Morph=new Class({Extends:Fx.CSS,initialize:function(b,a){this.element=this.subject=document.id(b);this.parent(a);
},set:function(a){if(typeof a=="string"){a=this.search(a);}for(var b in a){this.render(this.element,b,a[b],this.options.unit);}return this;},compute:function(e,d,c){var a={};
for(var b in e){a[b]=this.parent(e[b],d[b],c);}return a;},start:function(b){if(!this.check(b)){return this;}if(typeof b=="string"){b=this.search(b);}var e={},d={};
for(var c in b){var a=this.prepare(this.element,c,b[c]);e[c]=a.from;d[c]=a.to;}return this.parent(e,d);}});Element.Properties.morph={set:function(a){var b=this.retrieve("morph");
if(b){b.cancel();}return this.eliminate("morph").store("morph:options",$extend({link:"cancel"},a));},get:function(a){if(a||!this.retrieve("morph")){if(a||!this.retrieve("morph:options")){this.set("morph",a);
}this.store("morph",new Fx.Morph(this,this.retrieve("morph:options")));}return this.retrieve("morph");}};Element.implement({morph:function(a){this.get("morph").start(a);
return this;}});Fx.implement({getTransition:function(){var a=this.options.transition||Fx.Transitions.Sine.easeInOut;if(typeof a=="string"){var b=a.split(":");
a=Fx.Transitions;a=a[b[0]]||a[b[0].capitalize()];if(b[1]){a=a["ease"+b[1].capitalize()+(b[2]?b[2].capitalize():"")];}}return a;}});Fx.Transition=function(b,a){a=$splat(a);
return $extend(b,{easeIn:function(c){return b(c,a);},easeOut:function(c){return 1-b(1-c,a);},easeInOut:function(c){return(c<=0.5)?b(2*c,a)/2:(2-b(2*(1-c),a))/2;
}});};Fx.Transitions=new Hash({linear:$arguments(0)});Fx.Transitions.extend=function(a){for(var b in a){Fx.Transitions[b]=new Fx.Transition(a[b]);}};Fx.Transitions.extend({Pow:function(b,a){return Math.pow(b,a[0]||6);
},Expo:function(a){return Math.pow(2,8*(a-1));},Circ:function(a){return 1-Math.sin(Math.acos(a));},Sine:function(a){return 1-Math.sin((1-a)*Math.PI/2);
},Back:function(b,a){a=a[0]||1.618;return Math.pow(b,2)*((a+1)*b-a);},Bounce:function(f){var e;for(var d=0,c=1;1;d+=c,c/=2){if(f>=(7-4*d)/11){e=c*c-Math.pow((11-6*d-11*f)/4,2);
break;}}return e;},Elastic:function(b,a){return Math.pow(2,10*--b)*Math.cos(20*b*Math.PI*(a[0]||1)/3);}});["Quad","Cubic","Quart","Quint"].each(function(b,a){Fx.Transitions[b]=new Fx.Transition(function(c){return Math.pow(c,[a+2]);
});});var Request=new Class({Implements:[Chain,Events,Options],options:{url:"",data:"",headers:{"X-Requested-With":"XMLHttpRequest",Accept:"text/javascript, text/html, application/xml, text/xml, */*"},async:true,format:false,method:"post",link:"ignore",isSuccess:null,emulation:true,urlEncoded:true,encoding:"utf-8",evalScripts:false,evalResponse:false,noCache:false},initialize:function(a){this.xhr=new Browser.Request();
this.setOptions(a);this.options.isSuccess=this.options.isSuccess||this.isSuccess;this.headers=new Hash(this.options.headers);},onStateChange:function(){if(this.xhr.readyState!=4||!this.running){return;
}this.running=false;this.status=0;$try(function(){this.status=this.xhr.status;}.bind(this));this.xhr.onreadystatechange=$empty;if(this.options.isSuccess.call(this,this.status)){this.response={text:this.xhr.responseText,xml:this.xhr.responseXML};
this.success(this.response.text,this.response.xml);}else{this.response={text:null,xml:null};this.failure();}},isSuccess:function(){return((this.status>=200)&&(this.status<300));
},processScripts:function(a){if(this.options.evalResponse||(/(ecma|java)script/).test(this.getHeader("Content-type"))){return $exec(a);}return a.stripScripts(this.options.evalScripts);
},success:function(b,a){this.onSuccess(this.processScripts(b),a);},onSuccess:function(){this.fireEvent("complete",arguments).fireEvent("success",arguments).callChain();
},failure:function(){this.onFailure();},onFailure:function(){this.fireEvent("complete").fireEvent("failure",this.xhr);},setHeader:function(a,b){this.headers.set(a,b);
return this;},getHeader:function(a){return $try(function(){return this.xhr.getResponseHeader(a);}.bind(this));},check:function(){if(!this.running){return true;
}switch(this.options.link){case"cancel":this.cancel();return true;case"chain":this.chain(this.caller.bind(this,arguments));return false;}return false;},send:function(k){if(!this.check(k)){return this;
}this.running=true;var i=$type(k);if(i=="string"||i=="element"){k={data:k};}var d=this.options;k=$extend({data:d.data,url:d.url,method:d.method},k);var g=k.data,b=k.url,a=k.method.toLowerCase();
switch($type(g)){case"element":g=document.id(g).toQueryString();break;case"object":case"hash":g=Hash.toQueryString(g);}if(this.options.format){var j="format="+this.options.format;
g=(g)?j+"&"+g:j;}if(this.options.emulation&&!["get","post"].contains(a)){var h="_method="+a;g=(g)?h+"&"+g:h;a="post";}if(this.options.urlEncoded&&a=="post"){var c=(this.options.encoding)?"; charset="+this.options.encoding:"";
this.headers.set("Content-type","application/x-www-form-urlencoded"+c);}if(this.options.noCache){var f="noCache="+new Date().getTime();g=(g)?f+"&"+g:f;
}var e=b.lastIndexOf("/");if(e>-1&&(e=b.indexOf("#"))>-1){b=b.substr(0,e);}if(g&&a=="get"){b=b+(b.contains("?")?"&":"?")+g;g=null;}this.xhr.open(a.toUpperCase(),b,this.options.async);
this.xhr.onreadystatechange=this.onStateChange.bind(this);this.headers.each(function(m,l){try{this.xhr.setRequestHeader(l,m);}catch(n){this.fireEvent("exception",[l,m]);
}},this);this.fireEvent("request");this.xhr.send(g);if(!this.options.async){this.onStateChange();}return this;},cancel:function(){if(!this.running){return this;
}this.running=false;this.xhr.abort();this.xhr.onreadystatechange=$empty;this.xhr=new Browser.Request();this.fireEvent("cancel");return this;}});(function(){var a={};
["get","post","put","delete","GET","POST","PUT","DELETE"].each(function(b){a[b]=function(){var c=Array.link(arguments,{url:String.type,data:$defined});
return this.send($extend(c,{method:b}));};});Request.implement(a);})();Element.Properties.send={set:function(a){var b=this.retrieve("send");if(b){b.cancel();
}return this.eliminate("send").store("send:options",$extend({data:this,link:"cancel",method:this.get("method")||"post",url:this.get("action")},a));},get:function(a){if(a||!this.retrieve("send")){if(a||!this.retrieve("send:options")){this.set("send",a);
}this.store("send",new Request(this.retrieve("send:options")));}return this.retrieve("send");}};Element.implement({send:function(a){var b=this.get("send");
b.send({data:this,url:a||b.options.url});return this;}});Request.HTML=new Class({Extends:Request,options:{update:false,append:false,evalScripts:true,filter:false},processHTML:function(c){var b=c.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
c=(b)?b[1]:c;var a=new Element("div");return $try(function(){var d="<root>"+c+"</root>",g;if(Browser.Engine.trident){g=new ActiveXObject("Microsoft.XMLDOM");
g.async=false;g.loadXML(d);}else{g=new DOMParser().parseFromString(d,"text/xml");}d=g.getElementsByTagName("root")[0];if(!d){return null;}for(var f=0,e=d.childNodes.length;
f<e;f++){var h=Element.clone(d.childNodes[f],true,true);if(h){a.grab(h);}}return a;})||a.set("html",c);},success:function(d){var c=this.options,b=this.response;
b.html=d.stripScripts(function(e){b.javascript=e;});var a=this.processHTML(b.html);b.tree=a.childNodes;b.elements=a.getElements("*");if(c.filter){b.tree=b.elements.filter(c.filter);
}if(c.update){document.id(c.update).empty().set("html",b.html);}else{if(c.append){document.id(c.append).adopt(a.getChildren());}}if(c.evalScripts){$exec(b.javascript);
}this.onSuccess(b.tree,b.elements,b.html,b.javascript);}});Element.Properties.load={set:function(a){var b=this.retrieve("load");if(b){b.cancel();}return this.eliminate("load").store("load:options",$extend({data:this,link:"cancel",update:this,method:"get"},a));
},get:function(a){if(a||!this.retrieve("load")){if(a||!this.retrieve("load:options")){this.set("load",a);}this.store("load",new Request.HTML(this.retrieve("load:options")));
}return this.retrieve("load");}};Element.implement({load:function(){this.get("load").send(Array.link(arguments,{data:Object.type,url:String.type}));return this;
}});Request.JSON=new Class({Extends:Request,options:{secure:true},initialize:function(a){this.parent(a);this.headers.extend({Accept:"application/json","X-Request":"JSON"});
},success:function(a){this.response.json=JSON.decode(a,this.options.secure);this.onSuccess(this.response.json,a);}});;/*	SWFObject v2.2 <http://code.google.com/p/swfobject/> 
	is released under the MIT License <http://www.opensource.org/licenses/mit-license.php> 
*/
var swfobject=function(){var D="undefined",r="object",S="Shockwave Flash",W="ShockwaveFlash.ShockwaveFlash",q="application/x-shockwave-flash",R="SWFObjectExprInst",x="onreadystatechange",O=window,j=document,t=navigator,T=false,U=[h],o=[],N=[],I=[],l,Q,E,B,J=false,a=false,n,G,m=true,M=function(){var aa=typeof j.getElementById!=D&&typeof j.getElementsByTagName!=D&&typeof j.createElement!=D,ah=t.userAgent.toLowerCase(),Y=t.platform.toLowerCase(),ae=Y?/win/.test(Y):/win/.test(ah),ac=Y?/mac/.test(Y):/mac/.test(ah),af=/webkit/.test(ah)?parseFloat(ah.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):false,X=!+"\v1",ag=[0,0,0],ab=null;if(typeof t.plugins!=D&&typeof t.plugins[S]==r){ab=t.plugins[S].description;if(ab&&!(typeof t.mimeTypes!=D&&t.mimeTypes[q]&&!t.mimeTypes[q].enabledPlugin)){T=true;X=false;ab=ab.replace(/^.*\s+(\S+\s+\S+$)/,"$1");ag[0]=parseInt(ab.replace(/^(.*)\..*$/,"$1"),10);ag[1]=parseInt(ab.replace(/^.*\.(.*)\s.*$/,"$1"),10);ag[2]=/[a-zA-Z]/.test(ab)?parseInt(ab.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0}}else{if(typeof O.ActiveXObject!=D){try{var ad=new ActiveXObject(W);if(ad){ab=ad.GetVariable("$version");if(ab){X=true;ab=ab.split(" ")[1].split(",");ag=[parseInt(ab[0],10),parseInt(ab[1],10),parseInt(ab[2],10)]}}}catch(Z){}}}return{w3:aa,pv:ag,wk:af,ie:X,win:ae,mac:ac}}(),k=function(){if(!M.w3){return}if((typeof j.readyState!=D&&j.readyState=="complete")||(typeof j.readyState==D&&(j.getElementsByTagName("body")[0]||j.body))){f()}if(!J){if(typeof j.addEventListener!=D){j.addEventListener("DOMContentLoaded",f,false)}if(M.ie&&M.win){j.attachEvent(x,function(){if(j.readyState=="complete"){j.detachEvent(x,arguments.callee);f()}});if(O==top){(function(){if(J){return}try{j.documentElement.doScroll("left")}catch(X){setTimeout(arguments.callee,0);return}f()})()}}if(M.wk){(function(){if(J){return}if(!/loaded|complete/.test(j.readyState)){setTimeout(arguments.callee,0);return}f()})()}s(f)}}();function f(){if(J){return}try{var Z=j.getElementsByTagName("body")[0].appendChild(C("span"));Z.parentNode.removeChild(Z)}catch(aa){return}J=true;var X=U.length;for(var Y=0;Y<X;Y++){U[Y]()}}function K(X){if(J){X()}else{U[U.length]=X}}function s(Y){if(typeof O.addEventListener!=D){O.addEventListener("load",Y,false)}else{if(typeof j.addEventListener!=D){j.addEventListener("load",Y,false)}else{if(typeof O.attachEvent!=D){i(O,"onload",Y)}else{if(typeof O.onload=="function"){var X=O.onload;O.onload=function(){X();Y()}}else{O.onload=Y}}}}}function h(){if(T){V()}else{H()}}function V(){var X=j.getElementsByTagName("body")[0];var aa=C(r);aa.setAttribute("type",q);var Z=X.appendChild(aa);if(Z){var Y=0;(function(){if(typeof Z.GetVariable!=D){var ab=Z.GetVariable("$version");if(ab){ab=ab.split(" ")[1].split(",");M.pv=[parseInt(ab[0],10),parseInt(ab[1],10),parseInt(ab[2],10)]}}else{if(Y<10){Y++;setTimeout(arguments.callee,10);return}}X.removeChild(aa);Z=null;H()})()}else{H()}}function H(){var ag=o.length;if(ag>0){for(var af=0;af<ag;af++){var Y=o[af].id;var ab=o[af].callbackFn;var aa={success:false,id:Y};if(M.pv[0]>0){var ae=c(Y);if(ae){if(F(o[af].swfVersion)&&!(M.wk&&M.wk<312)){w(Y,true);if(ab){aa.success=true;aa.ref=z(Y);ab(aa)}}else{if(o[af].expressInstall&&A()){var ai={};ai.data=o[af].expressInstall;ai.width=ae.getAttribute("width")||"0";ai.height=ae.getAttribute("height")||"0";if(ae.getAttribute("class")){ai.styleclass=ae.getAttribute("class")}if(ae.getAttribute("align")){ai.align=ae.getAttribute("align")}var ah={};var X=ae.getElementsByTagName("param");var ac=X.length;for(var ad=0;ad<ac;ad++){if(X[ad].getAttribute("name").toLowerCase()!="movie"){ah[X[ad].getAttribute("name")]=X[ad].getAttribute("value")}}P(ai,ah,Y,ab)}else{p(ae);if(ab){ab(aa)}}}}}else{w(Y,true);if(ab){var Z=z(Y);if(Z&&typeof Z.SetVariable!=D){aa.success=true;aa.ref=Z}ab(aa)}}}}}function z(aa){var X=null;var Y=c(aa);if(Y&&Y.nodeName=="OBJECT"){if(typeof Y.SetVariable!=D){X=Y}else{var Z=Y.getElementsByTagName(r)[0];if(Z){X=Z}}}return X}function A(){return !a&&F("6.0.65")&&(M.win||M.mac)&&!(M.wk&&M.wk<312)}function P(aa,ab,X,Z){a=true;E=Z||null;B={success:false,id:X};var ae=c(X);if(ae){if(ae.nodeName=="OBJECT"){l=g(ae);Q=null}else{l=ae;Q=X}aa.id=R;if(typeof aa.width==D||(!/%$/.test(aa.width)&&parseInt(aa.width,10)<310)){aa.width="310"}if(typeof aa.height==D||(!/%$/.test(aa.height)&&parseInt(aa.height,10)<137)){aa.height="137"}j.title=j.title.slice(0,47)+" - Flash Player Installation";var ad=M.ie&&M.win?"ActiveX":"PlugIn",ac="MMredirectURL="+O.location.toString().replace(/&/g,"%26")+"&MMplayerType="+ad+"&MMdoctitle="+j.title;if(typeof ab.flashvars!=D){ab.flashvars+="&"+ac}else{ab.flashvars=ac}if(M.ie&&M.win&&ae.readyState!=4){var Y=C("div");X+="SWFObjectNew";Y.setAttribute("id",X);ae.parentNode.insertBefore(Y,ae);ae.style.display="none";(function(){if(ae.readyState==4){ae.parentNode.removeChild(ae)}else{setTimeout(arguments.callee,10)}})()}u(aa,ab,X)}}function p(Y){if(M.ie&&M.win&&Y.readyState!=4){var X=C("div");Y.parentNode.insertBefore(X,Y);X.parentNode.replaceChild(g(Y),X);Y.style.display="none";(function(){if(Y.readyState==4){Y.parentNode.removeChild(Y)}else{setTimeout(arguments.callee,10)}})()}else{Y.parentNode.replaceChild(g(Y),Y)}}function g(ab){var aa=C("div");if(M.win&&M.ie){aa.innerHTML=ab.innerHTML}else{var Y=ab.getElementsByTagName(r)[0];if(Y){var ad=Y.childNodes;if(ad){var X=ad.length;for(var Z=0;Z<X;Z++){if(!(ad[Z].nodeType==1&&ad[Z].nodeName=="PARAM")&&!(ad[Z].nodeType==8)){aa.appendChild(ad[Z].cloneNode(true))}}}}}return aa}function u(ai,ag,Y){var X,aa=c(Y);if(M.wk&&M.wk<312){return X}if(aa){if(typeof ai.id==D){ai.id=Y}if(M.ie&&M.win){var ah="";for(var ae in ai){if(ai[ae]!=Object.prototype[ae]){if(ae.toLowerCase()=="data"){ag.movie=ai[ae]}else{if(ae.toLowerCase()=="styleclass"){ah+=' class="'+ai[ae]+'"'}else{if(ae.toLowerCase()!="classid"){ah+=" "+ae+'="'+ai[ae]+'"'}}}}}var af="";for(var ad in ag){if(ag[ad]!=Object.prototype[ad]){af+='<param name="'+ad+'" value="'+ag[ad]+'" />'}}aa.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+ah+">"+af+"</object>";N[N.length]=ai.id;X=c(ai.id)}else{var Z=C(r);Z.setAttribute("type",q);for(var ac in ai){if(ai[ac]!=Object.prototype[ac]){if(ac.toLowerCase()=="styleclass"){Z.setAttribute("class",ai[ac])}else{if(ac.toLowerCase()!="classid"){Z.setAttribute(ac,ai[ac])}}}}for(var ab in ag){if(ag[ab]!=Object.prototype[ab]&&ab.toLowerCase()!="movie"){e(Z,ab,ag[ab])}}aa.parentNode.replaceChild(Z,aa);X=Z}}return X}function e(Z,X,Y){var aa=C("param");aa.setAttribute("name",X);aa.setAttribute("value",Y);Z.appendChild(aa)}function y(Y){var X=c(Y);if(X&&X.nodeName=="OBJECT"){if(M.ie&&M.win){X.style.display="none";(function(){if(X.readyState==4){b(Y)}else{setTimeout(arguments.callee,10)}})()}else{X.parentNode.removeChild(X)}}}function b(Z){var Y=c(Z);if(Y){for(var X in Y){if(typeof Y[X]=="function"){Y[X]=null}}Y.parentNode.removeChild(Y)}}function c(Z){var X=null;try{X=j.getElementById(Z)}catch(Y){}return X}function C(X){return j.createElement(X)}function i(Z,X,Y){Z.attachEvent(X,Y);I[I.length]=[Z,X,Y]}function F(Z){var Y=M.pv,X=Z.split(".");X[0]=parseInt(X[0],10);X[1]=parseInt(X[1],10)||0;X[2]=parseInt(X[2],10)||0;return(Y[0]>X[0]||(Y[0]==X[0]&&Y[1]>X[1])||(Y[0]==X[0]&&Y[1]==X[1]&&Y[2]>=X[2]))?true:false}function v(ac,Y,ad,ab){if(M.ie&&M.mac){return}var aa=j.getElementsByTagName("head")[0];if(!aa){return}var X=(ad&&typeof ad=="string")?ad:"screen";if(ab){n=null;G=null}if(!n||G!=X){var Z=C("style");Z.setAttribute("type","text/css");Z.setAttribute("media",X);n=aa.appendChild(Z);if(M.ie&&M.win&&typeof j.styleSheets!=D&&j.styleSheets.length>0){n=j.styleSheets[j.styleSheets.length-1]}G=X}if(M.ie&&M.win){if(n&&typeof n.addRule==r){n.addRule(ac,Y)}}else{if(n&&typeof j.createTextNode!=D){n.appendChild(j.createTextNode(ac+" {"+Y+"}"))}}}function w(Z,X){if(!m){return}var Y=X?"visible":"hidden";if(J&&c(Z)){c(Z).style.visibility=Y}else{v("#"+Z,"visibility:"+Y)}}function L(Y){var Z=/[\\\"<>\.;]/;var X=Z.exec(Y)!=null;return X&&typeof encodeURIComponent!=D?encodeURIComponent(Y):Y}var d=function(){if(M.ie&&M.win){window.attachEvent("onunload",function(){var ac=I.length;for(var ab=0;ab<ac;ab++){I[ab][0].detachEvent(I[ab][1],I[ab][2])}var Z=N.length;for(var aa=0;aa<Z;aa++){y(N[aa])}for(var Y in M){M[Y]=null}M=null;for(var X in swfobject){swfobject[X]=null}swfobject=null})}}();return{registerObject:function(ab,X,aa,Z){if(M.w3&&ab&&X){var Y={};Y.id=ab;Y.swfVersion=X;Y.expressInstall=aa;Y.callbackFn=Z;o[o.length]=Y;w(ab,false)}else{if(Z){Z({success:false,id:ab})}}},getObjectById:function(X){if(M.w3){return z(X)}},embedSWF:function(ab,ah,ae,ag,Y,aa,Z,ad,af,ac){var X={success:false,id:ah};if(M.w3&&!(M.wk&&M.wk<312)&&ab&&ah&&ae&&ag&&Y){w(ah,false);K(function(){ae+="";ag+="";var aj={};if(af&&typeof af===r){for(var al in af){aj[al]=af[al]}}aj.data=ab;aj.width=ae;aj.height=ag;var am={};if(ad&&typeof ad===r){for(var ak in ad){am[ak]=ad[ak]}}if(Z&&typeof Z===r){for(var ai in Z){if(typeof am.flashvars!=D){am.flashvars+="&"+ai+"="+Z[ai]}else{am.flashvars=ai+"="+Z[ai]}}}if(F(Y)){var an=u(aj,am,ah);if(aj.id==ah){w(ah,true)}X.success=true;X.ref=an}else{if(aa&&A()){aj.data=aa;P(aj,am,ah,ac);return}else{w(ah,true)}}if(ac){ac(X)}})}else{if(ac){ac(X)}}},switchOffAutoHideShow:function(){m=false},ua:M,getFlashPlayerVersion:function(){return{major:M.pv[0],minor:M.pv[1],release:M.pv[2]}},hasFlashPlayerVersion:F,createSWF:function(Z,Y,X){if(M.w3){return u(Z,Y,X)}else{return undefined}},showExpressInstall:function(Z,aa,X,Y){if(M.w3&&A()){P(Z,aa,X,Y)}},removeSWF:function(X){if(M.w3){y(X)}},createCSS:function(aa,Z,Y,X){if(M.w3){v(aa,Z,Y,X)}},addDomLoadEvent:K,addLoadEvent:s,getQueryParamValue:function(aa){var Z=j.location.search||j.location.hash;if(Z){if(/\?/.test(Z)){Z=Z.split("?")[1]}if(aa==null){return L(Z)}var Y=Z.split("&");for(var X=0;X<Y.length;X++){if(Y[X].substring(0,Y[X].indexOf("="))==aa){return L(Y[X].substring((Y[X].indexOf("=")+1)))}}}return""},expressInstallCallback:function(){if(a){var X=c(R);if(X&&l){X.parentNode.replaceChild(l,X);if(Q){w(Q,true);if(M.ie&&M.win){l.style.display="block"}}if(E){E(B)}}a=false}}}}();;
var BandeauDiaporama=function(el){
    var me=this;
    this.element=el;
    this.items=el.getChildren();
    this.current=this.items[0];
    this.previous=false;
    this.zindex=0;
    this.nav="";
    this.timelapse=false;
    this.paused=false;

    this.element.setStyle("position","relative");
    for(var i=0;i<this.items.length;i++){
        el=this.items[i];
        el.setStyle('position','absolute');
        el.setStyle('top','0');
	el.setStyle('left','0');
        el.setStyle('z-index','0');
        el.setStyle('opacity',0);
    }

    this.current.setStyle('opacity',1);
    /**
     * génére l'élément de nav
     */
    this.createNav=function(){
        this.nav=new Element("div");
	var cont=new Element("div");
	this.nav.grab(cont);
        this.nav.addClass("nav");
	this.nav.set("id","slider_buttons");
        var btn;
        var progress;
        for(var i=0;i<this.items.length;i++){
            btn=new Element("a");
            //btn.addClass("btn");
            progress=new Element("div");
            progress.addClass("progress");
            cont.grab(btn);
            //btn.grab(progress);

            btn.image=this.items[i];
            //btn.setStyle("width",String((100/this.items.length))+"%")
            this.items[i].btn=btn;
            this.items[i].progress=progress;

            btn.addEvent("click",function(){
                if(!me.paused){
                    me.show(this.image);
                    me.pause();
                }else{
                    if(me.current==this.image){
                      me.paused=false;
                      me.show(this.image);
                    }else{
                      me.show(this.image);
                      me.pause();
                    }
                    
                    
                }
            });
        }
        this.element.grab(this.nav);
    }
    this.pause=function(){
        me.paused=true;
        if(me.timelapse){
            clearTimeout(me.timelapse);
        }
        me.current.btnFx.cancel();
       // me.current.progress.setStyle("width","100%");
        //me.current.progress.setStyle("background-color","#ff0000");

    }
    /**
     * affiche un element du diapo
     */
    this.show=function(el){
        me.resetAll();
        me.current=el;
        me.zindex++;
        var duration=me.getDuration(el);


        //fade
        el.fxIn = new Fx.Tween(el, {
            duration: 'long'
        });
        el.fxIn.start("opacity",1);
        //progress btn
        el.btnFx = new Fx.Tween(el.progress, {
            duration: duration-10,
            transition: Fx.Transitions.linear,
            unit:"%"
        });
        //el.btnFx.start("width","0","100");
        //dessus
        el.setStyle("z-index",me.zindex);

        //lancera la prochaine
        if(!me.paused){
            if(me.timelapse){
                clearTimeout(me.timelapse);
            }
            me.timelapse=setTimeout(me.showNext,duration);
        }
	el.btn.addClass('active');

    }
    this.resetAll=function(){

        var it;
        for(var i=0;i<me.items.length;i++){
	     
            it=me.items[i];
	    it.btn.removeClass('active');
            if(it.fxIn){
               it.fxIn.cancel();
               //it.setStyle("opacity",0);
            }
            if(it.btnFx){
               it.btnFx.cancel();
	      
               //it.progress.setStyle("width",0);
            }
        }

    }
    /**
     * affiche le suivant dans la liste
     */
    this.showNext=function(){
        if(me.paused){
            return;
        }
        if(me.previous){
            me.previous.setStyle("opacity",0);
        }

        for(var i=0;i<me.items.length;i++){
            el=me.items[i];
            if(el==me.current){
                me.previous=me.current;
                if(i<me.items.length-1){
                    me.current=me.items[i+1];
                }else{
                    me.current=me.items[0];
                   // me.element.getElements(".progress").setStyle("width",0);
                }
                break;
            }
        }
        me.show(me.current);

 

    }
    /**
     * retourne la durée d'affichage d'un element
     */
    this.getDuration=function(el){
        var duration;
        if(el && el.get("data-duration")){
            duration=Number(el.get("data-duration"));
        }else{
            duration=5000;
        }
        return duration;
    }



    
    
    if(this.items.length>1){
        this.createNav();
        me.show(this.items[0]);
    }



}



;//MooTools More, <http://mootools.net/more>. Copyright (c) 2006-2009 Aaron Newton <http://clientcide.com/>, Valerio Proietti <http://mad4milk.net> & the MooTools team <http://mootools.net/developers>, MIT Style License.

MooTools.More={version:"1.2.5.1",build:"254884f2b83651bf95260eed5c6cceb838e22d8e"};(function(d,f){var c=/(.*?):relay\(((?:\(.*?\)|.)+)\)$/,b=/[+>~\s]/,g=function(h){var i=h.match(c);
return !i?{event:h}:{event:i[1],selector:i[2]};},a=function(n,h){var l=n.target;if(b.test(h=h.trim())){var k=this.getElements(h);for(var j=k.length;j--;
){var m=k[j];if(l==m||m.hasChild(l)){return m;}}}else{for(;l&&l!=this;l=l.parentNode){if(Element.match(l,h)){return document.id(l);}}}return null;};Element.implement({addEvent:function(l,k){var j=g(l);
if(j.selector){var i=this.retrieve("delegation:_delegateMonitors",{});if(!i[l]){var h=function(n){var m=a.call(this,n,j.selector);if(m){this.fireEvent(l,[n,m],0,m);
}}.bind(this);i[l]=h;d.call(this,j.event,h);}}return d.apply(this,arguments);},removeEvent:function(l,k){var j=g(l);if(j.selector){var i=this.retrieve("events");
if(!i||!i[l]||(k&&!i[l].keys.contains(k))){return this;}if(k){f.apply(this,[l,k]);}else{f.apply(this,l);}i=this.retrieve("events");if(i&&i[l]&&i[l].keys.length==0){var h=this.retrieve("delegation:_delegateMonitors",{});
f.apply(this,[j.event,h[l]]);delete h[l];}return this;}return f.apply(this,arguments);},fireEvent:function(l,i,h,n){var j=this.retrieve("events");var m,k;
if(i){m=i[0];k=i[1];}if(!j||!j[l]){return this;}j[l].keys.each(function(o){o.create({bind:n||this,delay:h,arguments:i})();},this);return this;}});})(Element.prototype.addEvent,Element.prototype.removeEvent);
try{if(typeof HTMLElement!="undefined"){HTMLElement.prototype.fireEvent=Element.prototype.fireEvent;}}catch(e){}Fx.Elements=new Class({Extends:Fx.CSS,initialize:function(b,a){this.elements=this.subject=$$(b);
this.parent(a);},compute:function(h,j,k){var c={};for(var d in h){var a=h[d],f=j[d],g=c[d]={};for(var b in a){g[b]=this.parent(a[b],f[b],k);}}return c;
},set:function(b){for(var c in b){if(!this.elements[c]){continue;}var a=b[c];for(var d in a){this.render(this.elements[c],d,a[d],this.options.unit);}}return this;
},start:function(c){if(!this.check(c)){return this;}var j={},k={};for(var d in c){if(!this.elements[d]){continue;}var g=c[d],a=j[d]={},h=k[d]={};for(var b in g){var f=this.prepare(this.elements[d],b,g[b]);
a[b]=f.from;h[b]=f.to;}}return this.parent(j,k);}});Fx.Accordion=new Class({Extends:Fx.Elements,options:{fixedHeight:false,fixedWidth:false,display:0,show:false,height:true,width:false,opacity:true,alwaysHide:false,trigger:"click",initialDisplayFx:true,returnHeightToAuto:true},initialize:function(){var c=Array.link(arguments,{container:Element.type,options:Object.type,togglers:$defined,elements:$defined});
this.parent(c.elements,c.options);this.togglers=$$(c.togglers);this.previous=-1;this.internalChain=new Chain();if(this.options.alwaysHide){this.options.wait=true;
}if($chk(this.options.show)){this.options.display=false;this.previous=this.options.show;}if(this.options.start){this.options.display=false;this.options.show=false;
}this.effects={};if(this.options.opacity){this.effects.opacity="fullOpacity";}if(this.options.width){this.effects.width=this.options.fixedWidth?"fullWidth":"offsetWidth";
}if(this.options.height){this.effects.height=this.options.fixedHeight?"fullHeight":"scrollHeight";}for(var b=0,a=this.togglers.length;b<a;b++){this.addSection(this.togglers[b],this.elements[b]);
}this.elements.each(function(f,d){if(this.options.show===d){this.fireEvent("active",[this.togglers[d],f]);}else{for(var g in this.effects){f.setStyle(g,0);
}}},this);if($chk(this.options.display)||this.options.initialDisplayFx===false){this.display(this.options.display,this.options.initialDisplayFx);}if(this.options.fixedHeight!==false){this.options.returnHeightToAuto=false;
}this.addEvent("complete",this.internalChain.callChain.bind(this.internalChain));},addSection:function(f,c){f=document.id(f);c=document.id(c);var g=this.togglers.contains(f);
this.togglers.include(f);this.elements.include(c);var a=this.togglers.indexOf(f);var b=this.display.bind(this,a);f.store("accordion:display",b);f.addEvent(this.options.trigger,b);
if(this.options.height){c.setStyles({"padding-top":0,"border-top":"none","padding-bottom":0,"border-bottom":"none"});}if(this.options.width){c.setStyles({"padding-left":0,"border-left":"none","padding-right":0,"border-right":"none"});
}c.fullOpacity=1;if(this.options.fixedWidth){c.fullWidth=this.options.fixedWidth;}if(this.options.fixedHeight){c.fullHeight=this.options.fixedHeight;}c.setStyle("overflow","hidden");
if(!g){for(var d in this.effects){c.setStyle(d,0);}}return this;},removeSection:function(f,b){var a=this.togglers.indexOf(f);var c=this.elements[a];var d=function(){this.togglers.erase(f);
this.elements.erase(c);this.detach(f);}.bind(this);if(this.now==a||b!=undefined){this.display($pick(b,a-1>=0?a-1:0)).chain(d);}else{d();}return this;},detach:function(b){var a=function(c){c.removeEvent(this.options.trigger,c.retrieve("accordion:display"));
}.bind(this);if(!b){this.togglers.each(a);}else{a(b);}return this;},display:function(a,b){if(!this.check(a,b)){return this;}b=$pick(b,true);a=($type(a)=="element")?this.elements.indexOf(a):a;
if(a==this.previous&&!this.options.alwaysHide){return this;}if(this.options.returnHeightToAuto){var d=this.elements[this.previous];if(d&&!this.selfHidden){for(var c in this.effects){d.setStyle(c,d[this.effects[c]]);
}}}if((this.timer&&this.options.wait)||(a===this.previous&&!this.options.alwaysHide)){return this;}this.previous=a;var f={};this.elements.each(function(j,h){f[h]={};
var g;if(h!=a){g=true;}else{if(this.options.alwaysHide&&((j.offsetHeight>0&&this.options.height)||j.offsetWidth>0&&this.options.width)){g=true;this.selfHidden=true;
}}this.fireEvent(g?"background":"active",[this.togglers[h],j]);for(var k in this.effects){f[h][k]=g?0:j[this.effects[k]];}},this);this.internalChain.clearChain();
this.internalChain.chain(function(){if(this.options.returnHeightToAuto&&!this.selfHidden){var g=this.elements[a];if(g){g.setStyle("height","auto");}}}.bind(this));
return b?this.start(f):this.set(f);}});var Accordion=new Class({Extends:Fx.Accordion,initialize:function(){this.parent.apply(this,arguments);var a=Array.link(arguments,{container:Element.type});
this.container=a.container;},addSection:function(c,b,f){c=document.id(c);b=document.id(b);var d=this.togglers.contains(c);var a=this.togglers.length;if(a&&(!d||f)){f=$pick(f,a-1);
c.inject(this.togglers[f],"before");b.inject(c,"after");}else{if(this.container&&!d){c.inject(this.container);b.inject(this.container);}}return this.parent.apply(this,arguments);
}});Fx.Scroll=new Class({Extends:Fx,options:{offset:{x:0,y:0},wheelStops:true},initialize:function(b,a){this.element=this.subject=document.id(b);this.parent(a);
var d=this.cancel.bind(this,false);if($type(this.element)!="element"){this.element=document.id(this.element.getDocument().body);}var c=this.element;if(this.options.wheelStops){this.addEvent("start",function(){c.addEvent("mousewheel",d);
},true);this.addEvent("complete",function(){c.removeEvent("mousewheel",d);},true);}},set:function(){var a=Array.flatten(arguments);if(Browser.Engine.gecko){a=[Math.round(a[0]),Math.round(a[1])];
}this.element.scrollTo(a[0]+this.options.offset.x,a[1]+this.options.offset.y);},compute:function(c,b,a){return[0,1].map(function(d){return Fx.compute(c[d],b[d],a);
});},start:function(c,h){if(!this.check(c,h)){return this;}var f=this.element.getScrollSize(),b=this.element.getScroll(),d={x:c,y:h};for(var g in d){var a=f[g];
if($chk(d[g])){d[g]=($type(d[g])=="number")?d[g]:a;}else{d[g]=b[g];}d[g]+=this.options.offset[g];}return this.parent([b.x,b.y],[d.x,d.y]);},toTop:function(){return this.start(false,0);
},toLeft:function(){return this.start(0,false);},toRight:function(){return this.start("right",false);},toBottom:function(){return this.start(false,"bottom");
},toElement:function(b){var a=document.id(b).getPosition(this.element);return this.start(a.x,a.y);},scrollIntoView:function(c,f,d){f=f?$splat(f):["x","y"];
var i={};c=document.id(c);var g=c.getPosition(this.element);var j=c.getSize();var h=this.element.getScroll();var a=this.element.getSize();var b={x:g.x+j.x,y:g.y+j.y};
["x","y"].each(function(k){if(f.contains(k)){if(b[k]>h[k]+a[k]){i[k]=b[k]-a[k];}if(g[k]<h[k]){i[k]=g[k];}}if(i[k]==null){i[k]=h[k];}if(d&&d[k]){i[k]=i[k]+d[k];
}},this);if(i.x!=h.x||i.y!=h.y){this.start(i.x,i.y);}return this;},scrollToCenter:function(c,f,d){f=f?$splat(f):["x","y"];c=$(c);var i={},g=c.getPosition(this.element),j=c.getSize(),h=this.element.getScroll(),a=this.element.getSize(),b={x:g.x+j.x,y:g.y+j.y};
["x","y"].each(function(k){if(f.contains(k)){i[k]=g[k]-(a[k]-j[k])/2;}if(i[k]==null){i[k]=h[k];}if(d&&d[k]){i[k]=i[k]+d[k];}},this);if(i.x!=h.x||i.y!=h.y){this.start(i.x,i.y);
}return this;}});Fx.Slide=new Class({Extends:Fx,options:{mode:"vertical",wrapper:false,hideOverflow:true,resetHeight:false},initialize:function(b,a){this.addEvent("complete",function(){this.open=(this.wrapper["offset"+this.layout.capitalize()]!=0);
if(this.open&&this.options.resetHeight){this.wrapper.setStyle("height","");}if(this.open&&Browser.Engine.webkit419){this.element.dispose().inject(this.wrapper);
}},true);this.element=this.subject=document.id(b);this.parent(a);var d=this.element.retrieve("wrapper");var c=this.element.getStyles("margin","position","overflow");
if(this.options.hideOverflow){c=$extend(c,{overflow:"hidden"});}if(this.options.wrapper){d=document.id(this.options.wrapper).setStyles(c);}this.wrapper=d||new Element("div",{styles:c}).wraps(this.element);
this.element.store("wrapper",this.wrapper).setStyle("margin",0);this.now=[];this.open=true;},vertical:function(){this.margin="margin-top";this.layout="height";
this.offset=this.element.offsetHeight;},horizontal:function(){this.margin="margin-left";this.layout="width";this.offset=this.element.offsetWidth;},set:function(a){this.element.setStyle(this.margin,a[0]);
this.wrapper.setStyle(this.layout,a[1]);return this;},compute:function(c,b,a){return[0,1].map(function(d){return Fx.compute(c[d],b[d],a);});},start:function(b,f){if(!this.check(b,f)){return this;
}this[f||this.options.mode]();var d=this.element.getStyle(this.margin).toInt();var c=this.wrapper.getStyle(this.layout).toInt();var a=[[d,c],[0,this.offset]];
var h=[[d,c],[-this.offset,0]];var g;switch(b){case"in":g=a;break;case"out":g=h;break;case"toggle":g=(c==0)?a:h;}return this.parent(g[0],g[1]);},slideIn:function(a){return this.start("in",a);
},slideOut:function(a){return this.start("out",a);},hide:function(a){this[a||this.options.mode]();this.open=false;return this.set([-this.offset,0]);},show:function(a){this[a||this.options.mode]();
this.open=true;return this.set([0,this.offset]);},toggle:function(a){return this.start("toggle",a);}});Element.Properties.slide={set:function(b){var a=this.retrieve("slide");
if(a){a.cancel();}return this.eliminate("slide").store("slide:options",$extend({link:"cancel"},b));},get:function(a){if(a||!this.retrieve("slide")){if(a||!this.retrieve("slide:options")){this.set("slide",a);
}this.store("slide",new Fx.Slide(this,this.retrieve("slide:options")));}return this.retrieve("slide");}};Element.implement({slide:function(d,f){d=d||"toggle";
var b=this.get("slide"),a;switch(d){case"hide":b.hide(f);break;case"show":b.show(f);break;case"toggle":var c=this.retrieve("slide:flag",b.open);b[c?"slideOut":"slideIn"](f);
this.store("slide:flag",!c);a=true;break;default:b.start(d,f);}if(!a){this.eliminate("slide:flag");}return this;}});(function(){var a=function(c,b){return(c)?($type(c)=="function"?c(b):b.get(c)):"";
};this.Tips=new Class({Implements:[Events,Options],options:{onShow:function(){this.tip.setStyle("display","block");},onHide:function(){this.tip.setStyle("display","none");
},title:"title",text:function(b){return b.get("rel")||b.get("href");},showDelay:100,hideDelay:100,className:"tip-wrap",offset:{x:16,y:16},windowPadding:{x:0,y:0},fixed:false},initialize:function(){var b=Array.link(arguments,{options:Object.type,elements:$defined});
this.setOptions(b.options);if(b.elements){this.attach(b.elements);}this.container=new Element("div",{"class":"tip"});},toElement:function(){if(this.tip){return this.tip;
}return this.tip=new Element("div",{"class":this.options.className,styles:{position:"absolute",top:0,left:0}}).adopt(new Element("div",{"class":"tip-top"}),this.container,new Element("div",{"class":"tip-bottom"}));
},attach:function(b){$$(b).each(function(d){var g=a(this.options.title,d),f=a(this.options.text,d);d.erase("title").store("tip:native",g).retrieve("tip:title",g);
d.retrieve("tip:text",f);this.fireEvent("attach",[d]);var c=["enter","leave"];if(!this.options.fixed){c.push("move");}c.each(function(i){var h=d.retrieve("tip:"+i);
if(!h){h=this["element"+i.capitalize()].bindWithEvent(this,d);}d.store("tip:"+i,h).addEvent("mouse"+i,h);},this);},this);return this;},detach:function(b){$$(b).each(function(d){["enter","leave","move"].each(function(f){d.removeEvent("mouse"+f,d.retrieve("tip:"+f)).eliminate("tip:"+f);
});this.fireEvent("detach",[d]);if(this.options.title=="title"){var c=d.retrieve("tip:native");if(c){d.set("title",c);}}},this);return this;},elementEnter:function(c,b){this.container.empty();
["title","text"].each(function(f){var d=b.retrieve("tip:"+f);if(d){this.fill(new Element("div",{"class":"tip-"+f}).inject(this.container),d);}},this);$clear(this.timer);
this.timer=(function(){this.show(b);this.position((this.options.fixed)?{page:b.getPosition()}:c);}).delay(this.options.showDelay,this);},elementLeave:function(c,b){$clear(this.timer);
this.timer=this.hide.delay(this.options.hideDelay,this,b);this.fireForParent(c,b);},fireForParent:function(c,b){b=b.getParent();if(!b||b==document.body){return;
}if(b.retrieve("tip:enter")){b.fireEvent("mouseenter",c);}else{this.fireForParent(c,b);}},elementMove:function(c,b){this.position(c);},position:function(f){if(!this.tip){document.id(this);
}var c=window.getSize(),b=window.getScroll(),g={x:this.tip.offsetWidth,y:this.tip.offsetHeight},d={x:"left",y:"top"},h={};for(var i in d){h[d[i]]=f.page[i]+this.options.offset[i];
if((h[d[i]]+g[i]-b[i])>c[i]-this.options.windowPadding[i]){h[d[i]]=f.page[i]-this.options.offset[i]-g[i];}}this.tip.setStyles(h);},fill:function(b,c){if(typeof c=="string"){b.set("html",c);
}else{b.adopt(c);}},show:function(b){if(!this.tip){document.id(this);}if(!this.tip.getParent()){this.tip.inject(document.body);}this.fireEvent("show",[this.tip,b]);
},hide:function(b){if(!this.tip){document.id(this);}this.fireEvent("hide",[this.tip,b]);}});})();;var Stats = {

	init : function( code , page , noHit ){


		$$('a').each(function(el){
		el.addEvent('click',
		    function(e){
			
			var ref=this.get("href");
			var reg=new RegExp("^"+rootUrl,"g");
			var reg2=new RegExp("^http","g");
			if (reg2.exec(ref) && !reg.test(ref)) {
			    Stats.ga._link(ref);
			    return false;
			}else{

			}
		    }
		);
		});

		Stats.code = code;
		try{
			ga = Stats.ga = _gat._getTracker( code );
			ga._setDomainName("none");
			ga._setAllowLinker(true);
			ga._setAllowHash(false);
			ga._link();
			ga._linkByPost();
		}catch(e){
		//console.log(e);
		}

		Stats.page( page , noHit );
		
	},

	page : function( _page , noHit ){
		Stats._page = _page;
		var extractXiti = /page\/(.*)\/page/i;
		var matchesPage = extractXiti.exec( ""+_page );
		try{
			Stats._pageXiti = matchesPage[1].split("/").join("::");
		}catch(e){}

		if( !noHit ){
			Stats.action( "view" );
		}
		
	},

	action : function( act, s2 ){
		try{

			if(!s2)
			{
				s2 = '';
			}

			Stats.ga._trackPageview( Stats._page + "/action/" + act +"/action/");

			splitted = act.split("/");
			if( splitted[0] == "view" ){
				splitted.shift();
			}

			splitted.unshift( Stats._pageXiti );

			xt_med('F',s2, splitted.join("::") );

		}
		catch(e){

		}
	},

	iframe : function( id ){
		var ifr = document.getElementById(id);
		var url = ifr.src;
		ifr.src = Stats.ga._getLinkerUrl( url );
	}

};

var Phone = {
	popup : function( url ) {

		var left = screen.availWidth - 440 ;

		if (url) {
			window.open(url, "call", "C, scrollbars=yes, width=430,height=378,top=0,left="+left) ;
		}
	}

}

function setLocale(href){
	var url=mainPiagetUrl+"?setLocale="+encodeURIComponent(href /*+ ( /~\/$/.test( href ) ? "" : "/" )*/ )+"&"+Stats.ga._getLinkerUrl(href);
	document.location=url;
	//Stats.ga._link(mainPiagetUrl+"?setLocale="+encodeURIComponent(href /*+ ( /~\/$/.test( href ) ? "" : "/" )*/ )+"&");
	return false;
}

function showTermsPopup(url) {
	termspopup = window.open(url,'popupterms', 'menubar=no, status=no, resizable=no, scrollbars=yes, width=380, height=400');
	termspopup.focus();
}

//-------------swf address-------------------

function onSwfAdressChange(){
	if(getSwfAdressHash()[1]=="bracelet"){
		setBracelet(getSwfAdressHash()[2])
	}
}
function getSwfAdressHash(){
	var splited=SWFAddress.getValue().split("/");
	return splited;
}

//-------------products----------------------

var hasFlash;
var mouseTimer;
var fadeElements=new Array();
function initFadeProduct(){

	fadeElements.push($("country"));
	fadeElements.push($("menu"));
	fadeElements.push($("links"));
	//fadeElements.push($("content"));
	fadeElements.push($("main3"));
	fadeElements.push($("newsletter"));
	//fadeElements.push($("bandeau"));
	fadeElements.push($("productInfos"));
	fadeElements.push($("main2"));
	fadeElements.push($("productMedia"))
	
	fadeElements.push($("collection"));
	fadeElements.push($("productNav"));
	fadeElements.push($("foot"));
	fadeElements.push($("product-bg"));
	
		//fadeOutProduct();

	mouseTimer=setTimeout("fadeOutProduct();",5000);
	$(document.body).addEvent('mousemove', function(){
		productFadeOnMove();
	});
	window.addEvent('scroll', function(){
		productFadeOnMove();
	});
	window.addEvent('blur', function(){
		fadeInProduct();
	});


}
function productFadeOnMove(){
	if(fadeElements[0].getStyle("opacity")<=0){
		fadeInProduct();
	}
	clearTimeout(mouseTimer);
	mouseTimer=setTimeout("fadeOutProduct();",10000);
}
function fadeInProduct(){

	for(i=0;i<fadeElements.length;i++){
		if(fadeElements[i]){
			fadeElements[i].fade('in');
		}
	}

	try{
		f = document["mediaviewer2"];
		f.afficheFond(true);
	}catch(e){}

}
function fadeOutProduct(){

	if( $('productInfos').hasClass('hidden') || $(document.body).getScroll().y > 0){
		fadeInProduct();
		return;
	}
	for(i=0;i<fadeElements.length;i++){
		if(fadeElements[i]) {
			fadeElements[i].fade('out', {
				duration:1500
			});
		}
	}
	try{
		f = document["mediaviewer2"];
		f.afficheFond(false);
	}catch(e){}
//}catch(e){}
}
function afficheBigPicture() {
	$$("#zoomMask").destroy();

	removes = $$("#main2, #root, #mediaviewer, #footer-wrap, #header-wrap");
	z = $("zoomMask");

	if( !z ){
		z = new Element('div', {

			id:"zoomMask",

			events: {
				click: function(){
					$('zoomMask').dispose();
					removes.each(function(e){
						e.setStyle("display","block");
						e.addClass("fade").addClass("in");
					});
					

				}
			}
		});
		z.inject($(document.body));

	}
	z.set('html','');

	var bigImage = new Element ('img', {
		src: medias[mediaId].bigPicture,
		"class": "productPicture fade"
		
	});
	
	bigImage.addEvent('load', function() { 
		this.addClass("in");
		removes.each(function(e){
			e.addClass("fade");
			e.setStyle("display","none");
		});

	} );
	bigImage.inject(z);
			
			
}

function afficheMedialoadFlashCallBack(e) {
	//console.log("j'ai pas flash !");
	
	if(!e.success) {
		hasFlash = false;
		var item=$("media"+mediaId);

		var m = medias[mediaId];
		var mime= m.mime;
		var media = m.media;
		var bigPicture=m.bigPicture;
		var mediaHtml=m.mediaHtml;

		if( mediaHtml ){
			$("image_print").set('src', mediaHtml);
		}else{
			$("image_print").set('src', media);
		}
		$("image_print").addEvent('load', function() {
			
			//console.log("j'utilise l'image print !");
			
			size = this.getSize();
			switch(m.verticalAlign){
				case "top":
					t = 0;
					break;
				case "bottom":
					t = 430 - size.y;
					break;
				default :
					t = (430 - size.y) / 2;
					break;
			}
			if(!isIPad()) {
				this.setStyle("margin-top", t ); /* this line crach on ipad*/
			}
			this.fade('hide').fade(1);
			resizeProductPage();
		} );
		//bigImage.onload = imload;
			
		if($$('#zoomIn')){
			$$('#zoomIn').destroy();	
		}

		if(bigPicture != undefined) {			
			var zoom = new Element('div', {
				id: 'zoomIn',
				'class':'zoom-icon',
				events: {
					click: afficheBigPicture
				}
			});
			zoom.inject($("bandeau"));
		}
		resizeProductPage();
	}
}
function afficheVideoloadFlashCallBack(e) {
	if(!e.success) {
		hasFlash = false;

                var mediaThumbnail=medias['productvideo'].mediaThumbnail;
                var mediaiPhone=medias['productvideo'].mediaiPhone;
                var subtitle=medias['productvideo'].subtitle;

                $$('#image_print_div img').setStyle('visibility','hidden');

                var mediaviewer = $('mediavideoContainer');

                var videoTag = new Element('video', {
                                id: 'item-video',
                                'class': 'item-video',
                                src: mediaiPhone,
                                poster : mediaThumbnail,
                                width: '100%',
                                height: '100%',
                                controls: 'controls'
                            });

                videoTag.inject(mediaviewer, 'top');

                var videoPoster = new Element('div', {
                                id: 'poster',
                                'class': 'poster',
                                style : "background:url('"+mediaThumbnail+"');"
                            });

                videoPoster.inject(mediaviewer, 'bottom');

                var playButton = new Element('div', {
                                id: 'playbutton'
                            });

                playButton.inject($('poster'), 'bottom');

                var videoSubtitle = new Element('div', {
                                id: 'srt',
                                datavideo: 'PiagetPlayer',
                                datasrt : subtitle
                            });

                videoSubtitle.inject(mediaviewer, 'bottom');

                var divSubtitle = new Element('div', {
                                id: 'subtitle'
                            });

                divSubtitle.inject(mediaviewer, 'bottom');




                $('item-video').addEventListener('play', function(){
                        videoInit($('srt'),$('item-video'));
                        if($('poster')){
                            $('poster').destroy();
                        }
                    });
	}
}
function toSeconds(t) {
    var s = 0.0
    if(t) {
      var p = t.split(':');
      for(i=0;i<p.length;i++)
            s = s * 60 + parseFloat(p[i].replace(',', '.'))
    }
    return s;
}

function strip(s) {
    return s.replace(/^\s+|\s+$/g,"");
}

function playSubtitles(subs,theVideoPlayed) {
    var srt = subs;
    $('subtitle').set('text','');
    srt = srt.replace(/\r\n|\r|\n/g, '\n')

    var subtitles = {};
    srt = strip(srt);

    var srt_ = srt.split('\n\n');

    for(s=0; s<srt_.length; s++) {
            st = srt_[s].split('\n');
            if(st.length >=2) {
              n = st[0];
              i = strip(st[1].split(' --> ')[0]);
              o = strip(st[1].split(' --> ')[1]);
              t = st[2];
              if(st.length > 2) {
                    for(j=3; j<st.length; j++)
                      t += '\n'+st[j];
              }
              is = toSeconds(i);
              os = toSeconds(o);
              subtitles[is] = {i: i, o: o, t: t};
            }
    }
    var currentSubtitle = -1;
    var ival = setInterval(function() {
        var currentTime = theVideoPlayed.currentTime;
        var subtitle = -1;
        for(s in subtitles) {
            if(s > currentTime)
              break
            subtitle = s;
        }
        if(subtitle > 0) {
            if (toSeconds(subtitles[subtitle].o) < currentTime) {
                  $('subtitle').set('html','');
            }
            else {
                  $('subtitle').set('html',subtitles[subtitle].t);
                  currentSubtitle=subtitle;
            }
        }

        if(theVideoPlayed.get("pause") == "true")
        {
            clearInterval(ival);
            $('subtitle').set('html','');
        }

    }, 50);
}

function videoInit(subtitleElement,theVideoPlayed){

    var videoId = subtitleElement.getProperty('dataVideo');
    var srtUrl = subtitleElement.getProperty('dataSrt');

    if(srtUrl) {
            var req = new Request.HTML({url:srtUrl, onSuccess: function(responseTree, responseElements, responseHTML, responseJavaScript) { playSubtitles(responseHTML,theVideoPlayed)} }).send();
    } else {
            playSubtitles(subtitleElement);
    }
}



var mediaId = 0;
function afficheMedia(id)
{
	//try{
	mediaId = id;
	var item=$("media"+id);
	var mime;
	mime=medias[id].mime;
	var media=medias[id].media;

	var bigPicture="";
	var bracelet="";
	var bigBracelet="";
	var verticalAlign="";
	var hasMirror="";

	if(medias[id].bigPicture){
		bigPicture=medias[id].bigPicture;
	}
	if(medias[id].bracelet){
		bracelet=medias[id].bracelet;
	}
	if(medias[id].bigBracelet){
		bigBracelet=medias[id].bigBracelet;
	}
	if(medias[id].verticalAlign){
		verticalAlign=medias[id].verticalAlign;
	}
	if(medias[id].hasMirror){
		hasMirror=medias[id].hasMirror;
	}

	$$('li[class=active]').removeClass("active");
	if(item){
		item.addClass("active");
	}

	if(mime && mime.indexOf("image") != -1 || mime.indexOf("application/x-shockwave-flash") != -1)
	{

		//$('mediaviewer_bg').addClass('hidden');
		$('mediaviewer').removeClass('hidden');
		$('mediaviewer2Container').removeClass('hidden');
		try{
		    $('mediavideoContainer').addClass('hidden');
		    if($('item-video')){
			$('mediavideoContainer').empty();
		    }
		}catch(e){alert("err 8 "+e);}
		if(id!="product"){
			if( $('productInfos') ){
			    $('productInfos').addClass('hidden');
			}
			if( $('bracelets') ){
				$('bracelets').addClass('hidden');
			}
		}else{
			if( $('productInfos') ){
			    $('productInfos').removeClass('hidden');
			}
			if( $('bracelets') ){
				$('bracelets').removeClass('hidden');
			}
		}
		/*
		if( $('bracelets') ){
			$('bracelets').removeClass('hidden');
		}
		 */
		VarsFicheProduit='';
		VarsFicheProduit+='LANGCODE='+langCode+'&';
		VarsFicheProduit+='RACINE=&';
		VarsFicheProduit+='FICHIER='+media+'&';
		VarsFicheProduit+='FICHIERZOOM='+bigPicture+'&';
		VarsFicheProduit+='BRACELET='+bracelet+'&';
		VarsFicheProduit+='VALIGN='+verticalAlign+'&';
		VarsFicheProduit+='MIRROR='+(hasMirror==1?'true':'false')+'&';
		VarsFicheProduit+='BRACELETZOOM='+bigBracelet+'&';
		VarsFicheProduit+='ZOOMLABEL=zoom&';
		swfobject.embedSWF(rootUrl+"/swf/com/photoProduit.swf?"+VarsFicheProduit, "mediaviewer2", "100%", "100%", "9.0.0", "", "", {
			bgcolor:"#000000",
			wmode:"opaque",
			menu:"false",
			allowfullscreen:"true",
			allowScriptAccess:"Always"
		},"",afficheMedialoadFlashCallBack);
		//console.log( $("mediaviewer2"));
		//try{
		//$('mediaviewer2').setStyle('height', window.getScrollSize().y);
		resizeProductPage();
	// }catch(e){}
	}
	else if(mime.indexOf("video") != -1)
	{
		if( $('bracelets') ){
			$('bracelets').addClass('hidden');
		}
		try{
		    $('mediavideoContainer').removeClass('hidden');
		}catch(e){alert("err 5 "+e);}
		try{
		$('mediaviewer2Container').addClass('hidden');
		}catch(e){alert("err 6 "+e);}
		$('productInfos').addClass('hidden');
		//$('mediaviewer_bg').removeClass('hidden');

		swfobject.embedSWF(rootUrl+"/swf/player/playerVideo.swf", "mediavideo", "992px", "100%", "9.0.40", rootUrl+"/swf/player/expressInstall.swf",
		{
			/*RACINE:"http://www.shic.cc/piaget/swf/player",
			STYLE_XML:"/xml/textsStyle.",
			XMLPLAYLIST:"http://www.shic.cc/piaget/possession/products/generatePlaylist/"+id,
			LANGCODE:"fr"*/

			RACINE:"http://www.shic.cc/piaget/swf/player",
			STYLE_XML:"/xml/textsStyle.",
			LANGCODE:langCode,
			URLVIDEO:media,
			URLPICTURE:bigPicture,
			AUTOPLAY: "true"

		},
		{
			bgcolor:"#000000",
			menu:"false",
			wmode:"opaque",
			allowfullscreen:"true"
		},"",afficheVideoloadFlashCallBack);
	}
//}catch(e){
//
//}
}








function afficheMediaLandingPage(id)
{
	mediaId = id;
	var item=$("media"+id);
	var mime;
	mime=medias[id].mime;
	var media=medias[id].media;

	$$('#productMedia li.active').removeClass("active");
	if(item){
		item.addClass("active");
	}

	if(mime && mime.indexOf("image") != -1 || mime.indexOf("application/x-shockwave-flash") != -1)
	{

		$('mediacontener').removeClass('hidden');
		$('mediavideoContainer').addClass('hidden');
		
		VarsFicheProduit='';
		VarsFicheProduit+='LANGCODE='+langCode+'&';
		VarsFicheProduit+='url='+media+'&';
		swfobject.embedSWF(rootUrl+"/swf/com/landingPageLoader.swf?"+VarsFicheProduit, "mediaimage", "100%", "100%", "9.0.0", "", "", {
			bgcolor:"transparent",
			wmode:"transparent",
			menu:"false",
			allowfullscreen:"true",
			allowScriptAccess:"Always"
		});
	}
	else if(mime.indexOf("video") != -1)
	{
		$('mediavideoContainer').removeClass('hidden');
		$('mediacontener').addClass('hidden');

		swfobject.embedSWF(rootUrl+"/swf/player/playerVideo.swf", "mediavideoContainer", "100%", "100%", "9.0.40", rootUrl+"/swf/player/expressInstall.swf",
		{
			RACINE:"http://www.shic.cc/piaget/swf/player",
			STYLE_XML:"/xml/textsStyle.",
			LANGCODE:langCode,
			URLVIDEO:media,
			URLPICTURE:bigPicture,
			AUTOPLAY: "true"
		},
		{
			bgcolor:"#000000",
			menu:"false",
			wmode:"opaque",
			allowfullscreen:"true"
		});
	}
}

function resizeLandingPage( tosmaller ){
	
	c = $("stage");
	m = $('form-lp').getPosition().y + $('form-lp').getSize().y + 30;
	f = $("footer-wrap");
	
	f.fade("out");

	var h = getDocHeight();
	
	c.style.height = m + "px";

	f.fade("in");

}






function productInit(widthVideo){
	
	SWFAddress.addEventListener(SWFAddressEvent.CHANGE, onSwfAdressChange);
		
	if(widthVideo){
            afficheMedia("productvideo");
        }else{
            afficheMedia("product");
        }
   
	resizeProductPage();
	$("footer-wrap").fade("in");
    
	if(getSwfAdressHash()[1]=="bracelet" && bracelets && bracelets[getSwfAdressHash()[2]]){
		var b=bracelets[getSwfAdressHash()[2]];
		setBracelet(b.reference);
	}
}

function __resizeProductPage( tosmaller ){
	//alert("coucou");
	c = $("content");
	m = $("mediaviewer2");
	ph = c.getStyle("height");
	f = $("footer-wrap");

	//if(!hasFlash){
		f.setStyle("display","none");
	//}
		
	c.style.height = m.style.height = 0;
	
	var h = getDocHeight();
	//console.log(h);
	c.style.height = h + "px";
	
	m.style.height = (h + $("foot").getSize().y)+"px";

	//if(!hasFlash){
		f.setStyle("top" , (h /*- $("foot").getSize().y*/+60) + "px" );
		//console.log('h='+h+' & top='+f.getStyle('top'));
		f.setStyle("display","block");
	//}	
	
	//console.log('je resize product page ! et la hauteur : '+h)
//}
}
function isSafari(){
	var agt=navigator.userAgent.toLowerCase();
    return agt == "safari";
}
function isIPad(){
    return navigator.platform == "iPad";
}
var DocHeight = 0;
function getDocHeight() {
	//foot = $('foot').getSize().y;
	//if( document.height ) return document.height;
	if(isIPad() || isSafari()) {
		
		if(DocHeight == 0) {
			DocHeight = document.body.scrollHeight;
		}
		
		DocHeight2 = $('tools').getSize().y + $('tools').getPosition().y; 
		
		return (DocHeight > DocHeight2)?DocHeight:DocHeight2;
		
		//return DocHeight;
	}
	

	if(window.getSize().y != window.getScrollSize().y)
	{
		
		DocHeight =  window.getScrollSize().y;
	}
	else
	{
		DocHeight = document.body.scrollHeight;
	}
	
	return DocHeight;

}

var braceletSelected;
function setBracelet(ref){
	urlBracelet=bracelets[ref].mainPicture;
	urlBraceletZoom=bracelets[ref].bigPicture;
	id=bracelets[ref].id;
	element=$("bracelet"+ref);
	if($('mediaviewer2') && $('mediaviewer2').setBracelet){
		$('mediaviewer2').setBracelet(urlBracelet,urlBraceletZoom);
		braceletSelected=$$(['li.bracelet.selected'])
		if(braceletSelected){
			braceletSelected.removeClass('selected');
		}
		element.addClass('selected');
		braceletSelected=element;
		SWFAddress.setValue("bracelet/"+ref);
	}else{
		//flash not ready
		setTimeout("setBracelet('"+ref+"')",1);
	}
}

///////////////////////////////////////////////// FORMULAIRES /////////////////////////////////////////////////


var Formulaire = new Class({

	Implements : Events,

	types: [ "contact" , "sendToAfriend" , "newsletter" ],

	emailRegexp : new RegExp("^([a-zA-Z0-9_-])+([.]?[a-zA-Z0-9_-]{1,})*@([a-zA-Z0-9-_]{2,}[.])+[a-zA-Z]{2,3}$"),

	scroller : new Fx.Scroll(window),

	currentCountry : 0,

	initialize : function( form ){
		this.form = form;
		
		this.type = this.types[0];

		this.message = form.getElement(".message");
		this.message.fade("hide");

		this.mandatory = this.message.getElement(".mandatory");
		//this.mandatory.setStyle('display','none');

		this.response = this.message.getElement(".response");
		//this.response.setStyle('display','none');
		
		this.types.each( function(t){
			if( this.form.hasClass(t) ){
				this.type = t;
			}
		}, this);

		this.catalogs = this.form.getElements('input[id^=options_catalog_]');
		this.catalogs.each( function(cb){
			cb.addEvent( "click" , this.checkCatalog.bindWithEvent(this) );
		}, this);

		this.subject = this.form.getElement('[id=request_objet]');
		if( this.subject ){
			this.subject.addEvent('change' , this.onSubject.bindWithEvent(this));
		}

		this.country = this.form.getElement('[id=address_country]');
		if( this.country ){
			this.country.addEvent('change', this.onCountry.bindWithEvent(this));
		}

		this.form.addEvent("submit" , this.submit.bindWithEvent(this));

		this.req = new Request.JSON({
			url: this.form.action,
			method: 'post',
			//data: 'pageId=<?=$page->id?>&productId=<?= $product->id ?>&submit=submit&typeForm='+this.type+generateDatas(form, new Array('person_civilite', 'person_nom', 'person_prenom', 'person_nom2', 'person_prenom2', 'person_email', 'ami_civilite', 'ami_nom', 'ami_prenom', 'ami_nom2', 'ami_prenom2', 'ami_email', 'address_adresse', 'address_ville', 'address_code_postal', 'address_country', 'request_message', 'request_objet', 'options_catalog_watches', 'options_catalog_jewellery', 'options_inscription_newsletter', 'base_domain', 'base_market', 'base_langue', 'base_id_page')),
			onSuccess: this.successJson.bind(this)

		});

		this.checkCatalog();
	},

	onCountry : function(e){
		cls = ""+this.form.get('class');
		cls.split(" ").each(function(cl){
			if( cl.indexOf("country") == 0 ){ // starts with "country"
				this.form.removeClass(cl);
			}
		}, this);

		this.currentCountry = this.country.get("value");
		this.form.addClass("country-" + this.currentCountry);
	},

	submit : function(e){
		
		e.stop();
		if( this.validate() ){
			this.send();
		}else{
			this.error();
		}
	},

	send : function(){

		act = this.type + "/post";

		if( this.type == "contact" && this.subject ){
			act += "/" + this.subject.get("value");
		}

		Stats.action( act , 7 );

		this.form.getElements("input[type=submit]").each(function(s){
			s.setProperty("disabled","disabled");
			s.addClass("loading");
		});

		this.req.send({
			data:this.form
		});
	},

	successJson : function(json, text) {

		this.form.getElements("input[type=submit]").each(function(s){
			s.removeProperty("disabled");
			s.removeClass("loading");
		});
		
		success = !!json.success && !json.error;

		if( success ){
			this.response.addClass("success");
			this.response.removeClass("error");
			this.response.set('html',json.success);
			this.reset();

		}else{
			this.response.set('html',"");

			if( json.errors ){
				json.errors.each( this.displayError.bind(this) );
			}else{
				this.response.set("html",json.error);
			}

			this.response.removeClass("success");
			this.response.addClass("error");
		//this.response.set('html',json.error);
		}

		//this.response.setStyle('display','block');
		this.message.fade("in");
		this.onSuccess( json , text , success );
	},
	
	displayError : function( swseError ){

		this.response.set("html","");
		
		switch( swseError.field ){
			case "firstName1" :
				id = "person_prenom";
				break;
			case "lastName1" :
				id = "person_nom";
				break;
			case "firstName2" :
				id = "person_prenom2";
				break;
			case "lastName2" :
				id = "person_nom2";
				break;
			case "email" :
				id = "person_email";
				break;
			default :
				id = swseError.field;
				break;
		}

		elem = this.form.getElement("#"+id);
		if( elem ){
			elem.getParent("p").addClass("invalid");
		}
		if( !this.response.getElement(".error-" + swseError.code ) ){
			this.response.grab( new Element( "div", {
				"text" : swseError.message,
				"class" : "error-" + swseError.code
			}));
		}
	},
	onSuccess : function( json , text , success ){
		
	},

	reset : function(){
		this.form.getElements("input,option,textarea").each( function(input){
			switch( input.get("type") ){
				case "submit":
				case "button":
				case "hidden":
					break;
				case "radio":
				case "checkbox":
					input.removeProperty("checked");
					break;

				default:
					
					switch( input.get("tag")  ){
						case "option":
							if( input.get("value") == 0 && !input.getProperty("disabled") ){
								input.setProperty("selected","selected");
							}else{
								input.removeProperty("selected");
							}
						
							break;
						
						default:
							input.set("value","");
							break;
					}
					break;

			}
			
		});

		this.onSubject();
		this.checkCatalog();

	},

	resetValidation : function(){

		this.message.fade("hide");
		//this.response.set("html","");
		this.mandatory.setStyle("display","none");

		form = this.form;

		form.getElements("p").each(function(p){
			p.removeClass("invalid");
		});

	},

	validate : function( e ){

		this.resetValidation();

		form = this.form;

		var error = false;

		form.getElements("input, select, textarea").each( function(input){
			
			var p = input.getParent("p");
			
			if( !p ){
				return;
			}

			if( p.getStyle("display") == "none" ){
				return;
			}
			

			if( p.hasClass("per-country") && !this.form.hasClass("country-"+this.currentCountry) ){
				//console.log("ignore ");
				//console.log(p);
				// ignored cause it's a field for certain countries only
				return;
			}

			cls = p.get("class");

			if( !cls ){
				return;
			}

			
			cls.split(" ").each( function( cl ){

				switch( cl ){
					case "valid-notempty":
						if( input.get("value") == "" ){
							error = true;
							p.addClass("invalid");
						}
						break;
					case "valid-notzero":
						if( input.get("value") <= 0 ){
							error = true;
							p.addClass("invalid");
						}
						break;
					case "valid-checked":
						if( input.get("type") == "radio" ){
							n = input.get("name");
							if( this.form.getElements("input[name='" + n +"']:checked").length == 0 ){
								error = true;
								p.addClass("invalid");
							}

						}else{
							if( !input.getProperty("checked") ){
								error = true;
								p.addClass("invalid");
							}
						}
						break;
					case "valid-email":
						if( !this.emailRegexp.test( input.get("value") ) ){
							error = true;
							p.addClass("invalid");
						}
						break;
					case "valid-date":

						d = {};

						["day","month","year"].each( function( i ){
							elem = p.getElement("input."+i);
							if( elem ){
								d[i] = elem.get("value");
							}
						} );

						if( d.day < 1 || d.day > 31 || d.month < 1 || d.month > 12 ){
							error = true;
							p.addClass("invalid");
						}

						//d = new Date( year.get("value"), month.get("value") , year.get("value") );
						
						break;


				}
			},this);
		}, this);

		return !error;
	},

	error : function(){

		this.response.set("html","");

		form = this.form;
		
		this.mandatory.setStyle('display','block');
		this.message.fade("in");

		this.scroller.toElement(this.mandatory);

		//envoyer les stats comme quoi le formulaire n'a pas pu etre envoye avec le type et la string 'invalid'
		if(form.get("id").indexOf("contact") != -1)
		{
			message = "contact/invalid";
		}
		else if(form.get("id").indexOf("send") != -1)
		{
			message = "sendToAfriend/invalid";
		}
		else if(form.get("id").indexOf("newsletter") != -1)
		{
			message = "newsletter/invalid";
		}
		else
		{
			message = form.get("id")+"/invalid";
		}

		Stats.action(message, '7');

		return false;
	},

	checkCatalog : function( e ){
	    
		var isCat = false;
		this.catalogs.each( function(cb){
			if( cb.get("checked") ){
				isCat = true;
			}
		}, this);

		this.form.getElements("p.valid-notempty-if-CATALOG").each(function(p){
			if( isCat ){
				p.addClass("valid-notempty");
			}else{
				p.removeClass("valid-notempty");
			}
		})

	},

	onSubject : function( e ){
		if( this.subject ){
			var comments = ( this.subject.get('value') == "COMMENTS" );

			this.form.getElements("p.valid-notempty-if-COMMENTS").each(function(p){
				if( comments ){
					p.addClass("valid-notempty");
				}else{
					p.removeClass("valid-notempty");
				}
			});
		}

	}

});

Formulaire.load = function( url , div , params ){
	
	div.set("load" , {data : params});
	div.get("load").addEvent("complete", function(){
		new Formulaire( div.getElement("form") );
	});
	div.load( url );

	return div.get("load");

};

var Stores = new Class({

	initialize : function( url ){
		this.url = url;
		this.elem = $('bandeau');
		this.nav = this.elem.getElement(".nav");

		this.storesmap = this.elem.getElement('#storesmap');
		this.areas = this.storesmap.getElements("area");
		
		this.map = this.elem.getElement(".map");
		this.background = this.map.getElement(".monde");
		this.zones = this.map.getElements(".zone");
		this.zones.each( function( z ){
			z.addClass("hidden");
		} );

		this.goButton = this.elem.getElement(".go");
		this.goButton.addClass("hidden");
		
		this.req = new Request.HTML({
			/*	link : 'chain'*/
			});
		this.req.addEvent("success", this.loaded.bind(this));

		this.places = [];
		this.selects = {};

		unFocus.History.addEventListener('historyChange', this.onHistory.bind(this));
		this.onHistory(unFocus.History.getCurrent());
	},

	onHistory : function( hash ){
		places = unescape(hash).split("/").filter(function(e,i){
			return !!e;
		});
		//console.log(this.places);
		this.onPlaces( places );
	},

	onPlaces : function( places ){

		this.goButton.addClass("hidden");

		this.places.each( function(place, i){
			var oldPath = this.places.slice(0,i+1).join("/");
			var path = places.slice( 0 , i+1 ).join("/");
			
			if( oldPath != path ){
				oldSelect = this.selects[oldPath];
				if(oldSelect){
					oldSelect.set("value","0");	
					oldSelect.getParent(".wrap").fade("hide").dispose();
				}
			}
			
		}, this);

		this.places = places;

		var loading = false;
		this.places.each( function(place,i){
			var path = places.slice( 0 , i+1 ).join("/");
			if( !this.selects[path] ){
				if(!loading) {
					loading = true;
					this.load(path);
				}
			//return false;
			}else{
				s = this.selects[path];
				this.add(s.getParent(".wrap"));
			}
		}, this);

		this.zone = this.places[0];

		this.zones.each( function(z){
			z.addClass("hidden");
			z.fade("out");
		});

		if( this.zone ){
			zoneCode = Stores.zoneCodesByName[this.zone];
			this.map.getElements(".zone-" + zoneCode ).each( function(e){
				e.removeClass("hidden");
				e.fade("in");
			});
		}

	},

	load : function( path ){
		this.req.get( this.url +"/"+ path );
	},

	loaded : function( tree, elems, html, js ){
		
		s = elems[0];
		//s.fade("hide");
		//console.log(s);
		select = s.getElement("select");
		select.addEvent("change",this.changed.bind(this));
		//select.addEvent("change",function (){alert(this.get('value'));});
		//console.log(s);

		this.add( s );
		this.onPlaces( this.places );

	},

	add : function( html ){
		select = html.getElement("select");
		
		name = select.get("name");
		cl = select.get("class");

		select.set("value","0");
		
		if( cl && ( prev = this.elem.getElement( "select."+cl ) ) ){
			prev.set("value","0");
			prev.getParent(".wrap").dispose();
		}

		this.selects[name] = select;

		html.fade("in");

		this.update();

	},

	changed : function( e ){

		t = e.target;
		v = e.target.get("value");

		if( v == "0" ){
			e.stop();
			this.update();
			return;
		}

		if( !t.hasClass("cities")){
			unFocus.History.addHistory( v );
		}else{
			this.update();
		}

	},

	update : function(){

		var lastSelect;

		order = [];

		this.places.each( function( place , i ){
			var path = this.places.slice( 0 , i+1 ).join("/");
			var nextPath = this.places.slice( 0 , i+2 ).join("/");

			select = this.selects[path];
			
			if( select ){
				order.push(select.getParent(".wrap"));

				//select.set("value",nextPath);
				//SOUS IE ça ne fonctionne pas ! On utilise donc une version generique pour tous les navigateurs
				for ( var z = 0; z < select.options.length; z++ )
				{if ( select.options[z].value == nextPath )
				    {
					select.selectedIndex = z;
				    }
				}

				lastSelect = select;
			}
			
		}, this );

		this.nav.getElement('.places').adopt(order);

		var showGo = false;
		if( lastSelect && lastSelect.hasClass("cities") ){
			city = lastSelect.get("value");
			if( city != "0" ){
				showGo = true;
			}	
		}
		if( showGo ){
			this.goButton.set("href",city);
			this.goButton.removeClass("hidden");
			this.goButton.fade("in");
		}else{
			this.goButton.fade("hide");
		}

	}


});

Stores.init = function( url , historyUrl ){
	Stores.url = url;
	new Request({
		url : historyUrl,
		evalResponse : true
	})
	.addEvent("success" , Stores.onHistoryLoaded )
	.send();

}
Stores.onHistoryLoaded = function(){
	Stores.current = new Stores( Stores.url );
}
Stores.zoneCodesByName = {};


function isdefined(variable) {
	return (typeof (window[variable]) == "undefined") ? false : true;
}
/*
function getFlashMajorVersion () {
	if(swfObjectIsDefined) {
		var playerVersion = swfobject.getFlashPlayerVersion();
		var majorVersion = playerVersion.major;
		return majorVersion;
	}
	return 0;
}*/

var hasFlash = true;

function initNoSwfCSS(rootUrl, langueCode) {

	if( isdefined("swfobject") && !swfobject.getFlashPlayerVersion().major /*!checkFlash()*/) {
		//var playerVersion = swfobject.getFlashPlayerVersion();
		//alert(checkFlash())
		hasFlash = false;
		var myLink = new Element('link', {
		    'type': 'text/css',
		    'rel': 'stylesheet',
		    'href': rootUrl + '/css/piaget_noswf.css?r='+(new Date()).getTime(),
		    'media': 'screen'
		});
		$$('head').adopt(myLink);
		if(langueCode == "ar")
		{
		    var myLink = new Element('link', {
			'type': 'text/css',
			'rel': 'stylesheet',
			'href': rootUrl + '/css/piaget_noswf.ar.css?r='+(new Date()).getTime(),
			'media': 'screen'
		    });
		    $$('head').adopt(myLink);
		}
	
		//<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;" />
	}
	
	/*if(langue == "ar") {
		var myLink = new Element('link', {
				'type': 'text/css',
				'rel': 'stylesheet',
				'href': rootUrl + '/css/piaget_noswf.'+langue+'.css?r='+(new Date()).getTime(),
				'media': 'screen'
			});
			$$('head').adopt(myLink);
	}*/
}
/*
function checkFlash() {
	//var majorVersion = getFlashMajorVersion ();
	//if(majorVersion == 0) { return false; } else {return true;}
	
	if( isdefined("swfobject") && !swfobject.getFlashPlayerVersion().major) {
		if(swfobject.getFlashPlayerVersion().major == 0)
			hasFlash = false;
		else 
			hasFlash = true;
	}
	else {
		hasFlash = false;
	}
	
	return hasFlash;
}*/

var swfObjectIsDefined = isdefined("swfobject");

function loadFlash(path, div, width, height, flashVersion,expressInstallSwf , flashvars, params, attributes) {
	if(hasFlash) {
		/*swfobject.embedSWF("<?= $rootUrl ?>/swf/com/piagetHomepage.swf", "flash", "100%", "416px", "9.0.0", "",
		{
			COLLECTION:"<?= $this->url("xml/home");?>",
            STYLE:"<?= $rootUrl ?>/swf/com/xml/textsStyle.<?= $langue->code ?>.xml",
			RTL:"<?= $langue->rtl ? "true" : "false" ?>"
		}, {bgcolor:"#000000",menu:"false",wmode:"opaque",base:"<?= $rootUrl ?>"});*/
		
		//var flashvars = { flashvars:variables_flash};
		//var attributes = {};
		
		swfobject.embedSWF(path, div, width, height, flashVersion, expressInstallSwf,
			flashvars, params, attributes);
	}
}

function loadFlashCallBack(e) {
	alert("e.success = " + e.success +"\ne.id = "+ e.id +"\ne.ref = "+ e.ref);
}
function initStoreSlide() {
	window.addEvent('domready',function(){
		var V5 = new viewer($$('#box5 ul li img'),{
			mode: 'alpha',
			interval: 5000,
			onWalk: function(current_index){
				handles5.removeClass('active');
				handles5[current_index].addClass('active');
			}

		})		
		var handles5 = $$('#slider_buttons a');
		handles5.removeClass('active');		
		handles5[0].addClass('active');
		handles5.each(function(el,i){
			el.addEvent('click',V5.walk.bind(V5,[i,true]));
		});
		V5.play(true);
	});
}
function initHomePageViewer() {
	window.addEvent('domready',function(){	
		if($$('.imageDiapo').length > 0) {
			var V5 = new viewer($$('.imageDiapo'),{
				mode: 'alpha',
				interval: 5000,
				onWalk: function(current_index){
					handles5.removeClass('active');
					handles5[current_index].addClass('active');
				}

			})		
			var handles5 = $$('#slider_buttons a');
			handles5.removeClass('active');		
			handles5[0].addClass('active');
			handles5.each(function(el,i){
				el.addEvent('click',V5.walk.bind(V5,[i,true]));
			});
			V5.play(true);	
		}
	});
}

function initCollectionsViewer() {
	window.addEvent('domready',function(){		
		var V5 = new viewer($$('#collections_lnk li'),{
			mode: 'alpha',
			interval: 2000,
			onWalk: function(current_index){
				handles5.removeClass('active');
				handles5.set('opacity','0.6');
				handles5[current_index].addClass('active');
				handles5[current_index].set('opacity','1');
			}

		})		
		var handles5 = $$('#collections_nav li a');
		handles5.set('opacity','0.6'); 
		handles5.removeClass('active');
		handles5[0].set('opacity','1'); 
		handles5.each(function(el,i){
			el.addEvent('click',V5.walk.bind(V5,[i,true]));
		});
		V5.play(true);	
	});
}
var activeLink = null;
function initFriseCollection(langue) {
	var myScroll = new iScroll('scroller', {
		bounceLock:true
	});
	var myValues = $('scroller').getCoordinates();
	if(langue == "ar") {
		myScroll.scrollTo( -myValues.width , 0 , '0' );
	}
	
	var products = $$('.col-product');
	$$(".col-product span").fade('hide');

	products.addEvent('click', function(event){
		event.stop();
		if(activeLink != null) {
			activeLink.getParent().getElement("span").fade('out');
		}
		if(activeLink == this) {
			document.location.href = this.href
		}
		else {
			activeLink = this;
		}
		this.getParent().getElement("span").fade('in');
		li = this.getParent("li");
		pos = li.getPosition(li.getParent("ul"));
		size = li.getSize();
		targetPos= -( pos.x - (li.getParent("#wrapper").getSize().x - size.x) / 2 );
		if(targetPos>0){
		    targetPos=0;
		}
		if(targetPos<-$('scroller').getSize().x+li.getParent("#wrapper").getSize().x){
		    targetPos=-$('scroller').getSize().x+li.getParent("#wrapper").getSize().x;
		}
		//alert(targetPos);
		 myScroll.scrollTo( targetPos , 0 , '500ms' );

		
	});
	
	products.addEvent('dblclick', function(event){
		document.location.href = this.href
	});

}

function initCatalogNavFrise() {
	var myScroll = new iScroll('scroller-cat', {
		bounceLock:true
	});
	return myScroll;
}

function initCraftship(langue) {
	var myScroll = new iScroll('scroller', {
		checkDOMChanges: false
	});

	var myValues = $('scroller').getCoordinates();
	if(langue == "ar") {
		myScroll.scrollTo( -myValues.width , 0 , '0' );
	}
			
	var items = $$('.seo ul li h2 a');
	
	items.addEvent('click', function(event){
		event.stop();
		
		var liX = this.getParent("li");
		if(activeLink) {
			$$(".seo ul li").set('class', 'li-webkit');
		}
		
		liX.set('class', 'li-webkit-over');

		if(activeLink == this) {
			document.location.href = this.href
		}
		else {
			activeLink = this;
		}

		pos = liX.getPosition( liX.getParent("ul") );
		size = liX.getSize();

		myScroll.scrollTo( - ( pos.x - (liX.getParent("#wrapper").getSize().x - size.x) / 2 ) , 0 , '500ms' );

	});

	items.addEvent('dblclick', function(event){
		document.location.href = this.href
	});
	
	return myScroll;
}

initHistory = initGoodies = initCraftship;
/*
 * please...
function initHistory() {
	var myScroll = new iScroll('scroller', { desktopCompatibility:true  });
	var items = $$('.seo ul li h2 a');
	
	items.addEvent('click', function(event){
		event.stop();
		
		//$$(".seo ul li").setStyle('width', '');		
		//$$(".seo ul li h2 a img").setStyle('width', '320px');
		//$$(".seo ul li h2 a img").setStyle('height', '100%');		
		//this.getParent().getParent().setStyle('width', '10%');
		
		//this.getElement("img").setStyle('width', '340px');
		
		var liX = this.getParent("li");
		if(activeLink) {
			$$(".seo ul li").set('class', 'li-webkit');
		}
		
		 liX.set('class', 'li-webkit-over');
		
		
		if(activeLink == this) {
			document.location.href = this.href
		}
		else {
			activeLink = this;
		}	
	});
	
	items.addEvent('dblclick', function(event){
		document.location.href = this.href
	});
}*/
/*
function initGoodies() {
	var myScroll = new iScroll('scroller', { bounceLock:true  });
	var items = $$('#scroller ul li a');
	
	items.addEvent('click', function(event){
		event.stop();
		
		if(activeLink == this) {
			document.location.href = this.href
		}
		else {
			activeLink = this;
		}	
	});
	
	items.addEvent('dblclick', function(event){
		document.location.href = this.href
	});
	
	return myScroll;
}*/

var iPadLabels = function () {

	function fix() {
		var labels = document.getElementsByTagName('label'), 
		target_id,
		el;
		for (var i = 0; labels[i]; i++) {
			if (labels[i].getAttribute('for')) {
				labels[i].onclick = labelClick;
			}
		}
	};

	function labelClick() {
		el = document.getElementById(this.getAttribute('for'));
		if (['radio', 'checkbox'].indexOf(el.getAttribute('type')) != -1) {
			el.setAttribute('selected', !el.getAttribute('selected'));
		} else {
			el.focus();
		}
	};

	return {
		fix: fix
	}

}();;