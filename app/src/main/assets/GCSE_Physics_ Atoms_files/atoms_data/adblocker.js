(function() {
    var head = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.type = 'text/javascript';
    var id1 = document.getElementById("hidebannerlocation").getAttribute("intid1");
    var id2 = document.getElementById("hidebannerlocation").getAttribute("intid2");
    if (id1 && id2) {
        script.src = '//fw.adsafeprotected.com/jsapi/' + id1 + '/' + id2 + '/skeleton.js?';
    } else {
        script.src = '//fw.adsafeprotected.com/jsapi/31724/9000/skeleton.js?';
    }
    script.async = true;
    head.appendChild(script);
})();
var __IntegralASConfig = {
    onAPIResult: function(integralApiData) {
        var riskValue = integralApiData.action;
        if (riskValue !== "passed") {
            for (i = 0; i < document.querySelectorAll("[id^=main]").length; i++) {
                var bannerId = document.querySelectorAll("[id^=main]")[i].getAttribute("id");
                if (bannerId.match(/main\S{3,}/g)) {
                    var htmlbannerstr = document.getElementById(bannerId);
                    var compStyle = window.getComputedStyle(document.getElementById(bannerId), "");
                    var height = parseInt(compStyle.getPropertyValue("height"));
                    var width = parseInt(compStyle.getPropertyValue("width"));
                    var newbanner = '<img src="//static.criteo.net/images/staticbanners/white_banner/white_banner_' + width + 'x' + height + '_1.gif" width="' + width + '" height="' + height + '">';

                    document.getElementById(bannerId).innerHTML = '';
                    document.getElementById(bannerId).innerHTML = newbanner;
                }
            }
        }
    }
};

/* IE polyfill */
if (!window.getComputedStyle) {
    window.getComputedStyle = function(el, pseudo) {
        this.el = el;
        this.getPropertyValue = function(prop) {
            var re = /(\-([a-z]){1})/g;
            if (prop == 'float') {
                prop = 'styleFloat';
            }
            if (re.test(prop)) {
                prop = prop.replace(re, function() {
                    return arguments[2].toUpperCase();
                });
            }
            return el.currentStyle[prop] ? el.currentStyle[prop] : null;
        }
        return this;
    }
}