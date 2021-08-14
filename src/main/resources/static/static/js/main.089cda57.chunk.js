(this["webpackJsonptexas-holdem-client"]=this["webpackJsonptexas-holdem-client"]||[]).push([[0],{107:function(e,t,a){},108:function(e,t,a){},116:function(e,t,a){},117:function(e,t,a){},118:function(e,t,a){},119:function(e,t,a){},120:function(e,t,a){},121:function(e,t,a){},122:function(e,t,a){},123:function(e,t,a){},124:function(e,t,a){},125:function(e,t,a){},126:function(e,t,a){},127:function(e,t,a){},128:function(e,t,a){},129:function(e,t,a){"use strict";a.r(t);var n,i=a(0),c=a.n(i),s=a(8),l=a.n(s),r=(a(86),a(87),a(88),a(14)),o=a(159),d=a(11),u=a(17),j=a(16),p=function e(){Object(j.a)(this,e),this.id=void 0,this.token=void 0,this.name=void 0,this.email=void 0,this.password=void 0,this.currentGame=void 0,this.wallet=void 0},m=function e(){Object(j.a)(this,e),this.auth=new p;var t=JSON.parse(localStorage.getItem("auth"));t&&(this.auth=t)};!function(e){e.Login="Login",e.Logout="Logout",e.UpdateWallet="UpdateWallet"}(n||(n={}));var b=function(e){return{type:n.Login,payload:e}},h=function(){return{type:n.Logout}},v=a(59),O=Object(v.a)({AuthState:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new m,t=arguments.length>1?arguments[1]:void 0,a=Object(u.a)({},e);switch(t.type){case n.Login:a.auth.token=t.payload.token,a.auth.name=t.payload.name,a.auth.id=t.payload.id,a.auth.wallet=t.payload.wallet,localStorage.setItem("auth",JSON.stringify(a.auth));break;case n.Logout:a.auth=new p,localStorage.removeItem("auth");break;case n.UpdateWallet:a.auth.wallet=t.payload,localStorage.setItem("auth",JSON.stringify(a.auth))}return a}}),x=Object(v.b)(O),f=function(e){var t,a,n,i;e.response?(console.assert(null===e||void 0===e||null===(t=e.response)||void 0===t||null===(a=t.data)||void 0===a?void 0:a.message),alert(null===e||void 0===e||null===(n=e.response)||void 0===n||null===(i=n.data)||void 0===i?void 0:i.message)):alert("ERROR !!!")},g=a(60),y=a(58),N=function e(){Object(j.a)(this,e)},S=new(function(e){Object(g.a)(a,e);var t=Object(y.a)(a);function a(){var e;Object(j.a)(this,a);for(var n=arguments.length,i=new Array(n),c=0;c<n;c++)i[c]=arguments[c];return(e=t.call.apply(t,[this].concat(i))).urls={localUrl:"https://buddy-holdem.herokuapp.com/"},e}return a}(N)),C=a(36),I=a.n(C),P=I.a.create();P.interceptors.request.use((function(e){return e.headers={token:x.getState().AuthState.auth.token},e})),P.interceptors.response.use((function(e){return e}),(function(e){var t;return 401===(null===e||void 0===e||null===(t=e.response)||void 0===t?void 0:t.data.status)&&x.dispatch(h()),Promise.reject(e)}));var L=P,w=(a(107),a(108),a.p+"static/media/logo.71852568.png"),D=a(1),A=function(){return Object(D.jsx)("div",{className:"Logo",children:Object(D.jsx)("img",{src:w,alt:"logo"})})},T=function(){var e=Object(i.useState)(x.getState().AuthState.auth.name),t=Object(r.a)(e,2),a=t[0],n=t[1],c=Object(i.useState)(x.getState().AuthState.auth.wallet),s=Object(r.a)(c,2),l=s[0],u=s[1],j=Object(d.g)();Object(i.useEffect)((function(){var e=x.subscribe((function(){n(x.getState().AuthState.auth.name),u(x.getState().AuthState.auth.wallet)}));return function(){e()}}),[]);return Object(D.jsxs)("div",{className:"Header",children:[Object(D.jsx)("div",{className:"LoginClass",children:Object(D.jsx)(A,{})}),Object(D.jsx)("h3",{children:"TEXAS HOLD'EM"}),Object(D.jsxs)("div",{className:"LoginClass",children:[a&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){L.post(S.urls.localUrl+"api/player/buy").then((function(e){alert("You have: "+e.data+" Chips.")})).catch((function(e){f(e)}))},children:"Buy 1000 Chips"}),a&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){x.getState().AuthState.auth.currentGame?L.post(S.urls.localUrl+"api/game/leave/"+x.getState().AuthState.auth.currentGame).then((function(){x.dispatch(h()),j.push("/login")})).catch((function(e){f(e)})):(x.dispatch(h()),j.push("/login"))},children:"Logout"}),a&&Object(D.jsxs)("span",{children:["Hello ",a," "]}),l&&Object(D.jsxs)("span",{children:["Wallet: ",l," "]}),!a&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){j.push("/login")},children:"Login"}),!a&&Object(D.jsx)("span",{children:"Hello user,"})]})]})},H=a(26),E=a(163),U=a(164),k=a(170),R=a(39),G=(a(116),Object(E.a)({textfieldInput:{color:"lightGray"},buttonStyle:{textTransform:"lowercase"}}));var F=function(){var e=Object(d.g)(),t=G(),a=Object(R.a)(),n=a.register,i=a.handleSubmit;return Object(D.jsx)("div",{className:"CreateGame",children:Object(D.jsxs)("form",{onSubmit:i((function(t){L.post(S.urls.localUrl+"api/game/createGame",t).then((function(a){e.push("/game/"+t.name)})).catch((function(e){f(e)}))})),className:"CreateGameForm",children:[Object(D.jsx)(U.a,{variant:"h5",component:"h1",children:"Create Game"}),Object(D.jsx)(k.a,Object(u.a)(Object(u.a)({InputLabelProps:{className:t.textfieldInput},InputProps:{className:t.textfieldInput},name:"name"},n("name")),{},{variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"name",type:"text",autoFocus:!0})),Object(D.jsx)(k.a,Object(u.a)({InputLabelProps:{className:t.textfieldInput},InputProps:{className:t.textfieldInput,inputProps:{min:10,max:100,step:10}},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"Small Blind",type:"number",name:"smallBlindBet"},n("smallBlindBet"))),Object(D.jsx)("br",{}),Object(D.jsx)("br",{}),Object(D.jsx)(o.a,{type:"submit",variant:"contained",color:"secondary",children:"Submit"})]})})},W=(a(117),a(118),a.p+"static/media/H2.3959d835.png"),B=a.p+"static/media/H3.bc5f19b0.png",q=a.p+"static/media/H4.9dd01576.png",J=a.p+"static/media/H5.610fbce0.png",K=a.p+"static/media/H6.2a7beede.png",M=a.p+"static/media/H7.75394053.png",V=a.p+"static/media/H8.198d2648.png",X=a.p+"static/media/H9.ad4f620d.png",Y=a.p+"static/media/H10.bef4a242.png",z=a.p+"static/media/H11.83a1affc.png",Q=a.p+"static/media/H12.ad6cbbbe.png",Z=a.p+"static/media/H13.1a8db36c.png",$=a.p+"static/media/H14.4d5d7e62.png",_=a.p+"static/media/C2.4283e273.png",ee=a.p+"static/media/C3.8cc1b573.png",te=a.p+"static/media/C4.4565d3b6.png",ae=a.p+"static/media/C5.df98fef6.png",ne=a.p+"static/media/C6.10f395a3.png",ie=a.p+"static/media/C7.e7b7382d.png",ce=a.p+"static/media/C8.df653b38.png",se=a.p+"static/media/C9.9a75f0be.png",le=a.p+"static/media/C10.cae8c318.png",re=a.p+"static/media/C11.244ce992.png",oe=a.p+"static/media/C12.54c9a138.png",de=a.p+"static/media/C13.cd97114b.png",ue=a.p+"static/media/C14.6e061197.png",je=a.p+"static/media/D2.5f1f6786.png",pe=a.p+"static/media/D3.ca60ad34.png",me=a.p+"static/media/D4.62438479.png",be=a.p+"static/media/D5.4f5306cb.png",he=a.p+"static/media/D6.239d0215.png",ve=a.p+"static/media/D7.b892a896.png",Oe=a.p+"static/media/D8.f0fc0837.png",xe=a.p+"static/media/D9.71a6ae93.png",fe=a.p+"static/media/D10.58cd78b8.png",ge=a.p+"static/media/D11.a604ebd7.png",ye=a.p+"static/media/D12.0d2127e5.png",Ne=a.p+"static/media/D13.5555c42c.png",Se=a.p+"static/media/D14.55ad3360.png",Ce=a.p+"static/media/S2.6460e9d3.png",Ie=a.p+"static/media/S3.f4100363.png",Pe=a.p+"static/media/S4.bdf8fc38.png",Le=a.p+"static/media/S5.af292142.png",we=a.p+"static/media/S6.0f9328ba.png",De=a.p+"static/media/S7.836588f9.png",Ae=a.p+"static/media/S8.abb7b54b.png",Te=a.p+"static/media/S9.8141f0d5.png",He=a.p+"static/media/S10.3a98801b.png",Ee=a.p+"static/media/S11.2a19a2ec.png",Ue=a.p+"static/media/S12.5c0f69e0.png",ke=a.p+"static/media/S13.8f004ced.png",Re=a.p+"static/media/S14.d8c12d7e.png",Ge=function(e){switch(e){case"H2":return W;case"H3":return B;case"H4":return q;case"H5":return J;case"H6":return K;case"H7":return M;case"H8":return V;case"H9":return X;case"H10":return Y;case"H11":return z;case"H12":return Q;case"H13":return Z;case"H14":return $;case"C2":return _;case"C3":return ee;case"C4":return te;case"C5":return ae;case"C6":return ne;case"C7":return ie;case"C8":return ce;case"C9":return se;case"C10":return le;case"C11":return re;case"C12":return oe;case"C13":return de;case"C14":return ue;case"D2":return je;case"D3":return pe;case"D4":return me;case"D5":return be;case"D6":return he;case"D7":return ve;case"D8":return Oe;case"D9":return xe;case"D10":return fe;case"D11":return ge;case"D12":return ye;case"D13":return Ne;case"D14":return Se;case"S2":return Ce;case"S3":return Ie;case"S4":return Pe;case"S5":return Le;case"S6":return we;case"S7":return De;case"S8":return Ae;case"S9":return Te;case"S10":return He;case"S11":return Ee;case"S12":return Ue;case"S13":return ke;case"S14":return Re}};var Fe=function(e){var t="CardClass";return Object(D.jsxs)("div",{className:"Community",children:[e.cards[0]&&Object(D.jsx)("div",{className:t,children:Object(D.jsx)("img",{alt:null===e||void 0===e?void 0:e.cards[0],src:Ge(null===e||void 0===e?void 0:e.cards[0])})}),e.cards[1]&&Object(D.jsx)("div",{className:t,children:Object(D.jsx)("img",{alt:null===e||void 0===e?void 0:e.cards[1],src:Ge(null===e||void 0===e?void 0:e.cards[1])})}),e.cards[2]&&Object(D.jsx)("div",{className:t,children:Object(D.jsx)("img",{alt:null===e||void 0===e?void 0:e.cards[2],src:Ge(null===e||void 0===e?void 0:e.cards[2])})}),e.cards[3]&&Object(D.jsx)("div",{className:t,children:Object(D.jsx)("img",{alt:null===e||void 0===e?void 0:e.cards[3],src:Ge(null===e||void 0===e?void 0:e.cards[3])})}),e.cards[4]&&Object(D.jsx)("div",{className:t,children:Object(D.jsx)("img",{alt:null===e||void 0===e?void 0:e.cards[4],src:Ge(null===e||void 0===e?void 0:e.cards[4])})})]})},We=function e(){Object(j.a)(this,e),this.id=void 0,this.wallet=void 0,this.name=void 0,this.email=void 0,this.password=void 0,this.card1=void 0,this.card2=void 0,this.lastAct=void 0,this.compareString=void 0,this.lastAmount=void 0,this.winner=void 0,this.isAllowReveal=void 0};a(119);var Be=function(e){return e.player?Object(D.jsxs)("div",{className:"TablePlayer",children:[Object(D.jsx)("div",{children:e.player.name}),Object(D.jsx)("div",{children:e.player.wallet}),Object(D.jsxs)("div",{className:"CardsDiv",children:[e.player.card1&&Object(D.jsx)("div",{className:"CardClass",children:Object(D.jsx)("img",{alt:e.player.card1,src:Ge(e.player.card1)})}),e.player.card1&&Object(D.jsx)("div",{className:"CardClass",children:Object(D.jsx)("img",{alt:e.player.card2,src:Ge(e.player.card2)})})]})]}):null};a(120),a(121);var qe=function(){return Object(D.jsx)("div",{className:"Dealer",children:"Dealer"})};a(122);var Je=function(){return Object(D.jsx)("div",{className:"SmallBlind",children:"SB"})};a(123);var Ke=function(){return Object(D.jsx)("div",{className:"BigBlind",children:"BB"})};var Me,Ve=function(e){var t,a;e.dealer<8?(t=e.dealer+1,a=e.dealer+2):8===e.dealer?(t=9,a=0):9===e.dealer&&(t=0,a=1);var n,i=e.myPosition===e.dealer,c=e.myPosition===t,s=e.myPosition===a;return e.player?Object(D.jsx)("div",{className:"InnerTablePlayer",children:Object(D.jsx)("div",{className:e.position,children:Object(D.jsxs)("div",{className:"Role",children:[Object(D.jsxs)("div",{children:[i&&Object(D.jsx)(qe,{}),c&&Object(D.jsx)(Je,{}),s&&Object(D.jsx)(Ke,{})]}),"\xa0",Object(D.jsxs)("div",{children:[Object(D.jsx)("div",{children:e.player.lastAmount}),(null===(n=e.player)||void 0===n?void 0:n.winner)?Object(D.jsx)("div",{className:"Winner",children:"WINNER"}):Object(D.jsx)("div",{children:e.player.lastAct})]})]})})}):null};!function(e){e.BET="BET",e.CHECK="CHECK",e.CALL="CALL",e.RAISE="RAISE",e.FOLD="FOLD",e.WAITING="WAITING"}(Me||(Me={}));var Xe=a(173),Ye=a(174),ze=a(167),Qe=a(172),Ze=a(169),$e=(a(124),Object(E.a)((function(e){return Object(Xe.a)({formControl:{margin:e.spacing(1),minWidth:120},selectEmpty:{marginTop:e.spacing(2)}})})));var _e,et=function(e){var t,a,n=$e(),i=Object(d.g)(),c=(null===(t=e.game)||void 0===t?void 0:t.admin)===x.getState().AuthState.auth.id&&"WAITING"===(null===(a=e.game)||void 0===a?void 0:a.status.toString());return Object(D.jsxs)("div",{className:"Play",children:[c&&Object(D.jsx)(o.a,{color:"primary",variant:"contained",onClick:function(){L.post(S.urls.localUrl+"api/game/startGame/"+e.game.name).then((function(t){e.ping()})).catch((function(e){alert(e)}))},children:"Start Game"}),Object(D.jsx)(o.a,{variant:"contained",onClick:function(){var t;L.post(S.urls.localUrl+"api/game/leave/"+(null===(t=e.game)||void 0===t?void 0:t.name)).then((function(){i.push("/join")})).catch((function(e){f(e)}))},children:"Leave Game"}),e.plays.includes(Me.CHECK)&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){L.post(S.urls.localUrl+"api/game/play/"+Me.CHECK+"/0").then((function(t){e.ping()})).catch((function(e){alert(e)}))},children:"Check"}),e.plays.includes(Me.FOLD)&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){L.post(S.urls.localUrl+"api/game/play/FOLD/-1").then((function(t){e.ping()})).catch((function(e){alert(e)}))},children:"Fold"}),e.plays.includes(Me.CALL)&&Object(D.jsx)(o.a,{variant:"contained",onClick:function(){var t=e.game.players.find((function(t){return t.id===e.game.activePlayers[e.game.lastRaised]})).lastAmount;L.post(S.urls.localUrl+"api/game/play/"+Me.CALL+"/"+t).then((function(t){e.ping()})).catch((function(e){alert(e)}))},children:"Call"}),e.plays.includes(Me.BET)&&Object(D.jsxs)(k.a,{variant:"filled",name:"bet",select:!0,label:"Bet",margin:"dense",onChange:function(t){t.preventDefault(),L.post(S.urls.localUrl+"api/game/play/BET/"+t.target.value).then((function(t){e.ping()})).catch((function(e){alert(e)}))},children:[Object(D.jsx)(Ye.a,{value:10,children:"10"},"10"),Object(D.jsx)(Ye.a,{value:20,children:"20"},"20"),Object(D.jsx)(Ye.a,{value:30,children:"30"},"30"),Object(D.jsx)(Ye.a,{value:40,children:"40"},"40"),Object(D.jsx)(Ye.a,{value:50,children:"50"},"50"),Object(D.jsx)(Ye.a,{value:100,children:"50"},"100"),Object(D.jsx)(Ye.a,{value:-1,children:"ALL IN"},"-1")]}),e.plays.includes(Me.RAISE)&&Object(D.jsxs)(ze.a,{variant:"outlined",className:n.formControl,children:[Object(D.jsx)(Qe.a,{id:"demo-simple-select-outlined-label",children:"Raise"}),Object(D.jsxs)(Ze.a,{labelId:"demo-simple-select-outlined-label",id:"demo-simple-select-outlined",onChange:function(t){t.preventDefault();var a=e.game.activePlayers[e.game.lastRaised],n=e.game.players.find((function(e){return e.id===a})),i=null===n||void 0===n?void 0:n.lastAmount;parseInt(t.target.value)<=i?alert("invalid amount"):L.post(S.urls.localUrl+"api/game/play/RAISE/"+t.target.value).then((function(t){e.ping()})).catch((function(e){alert(e)}))},label:"Raise",margin:"dense",children:[Object(D.jsx)(Ye.a,{value:10,children:"10"},"10"),Object(D.jsx)(Ye.a,{value:20,children:"20"},"20"),Object(D.jsx)(Ye.a,{value:30,children:"30"},"30"),Object(D.jsx)(Ye.a,{value:40,children:"40"},"40"),Object(D.jsx)(Ye.a,{value:50,children:"50"},"50"),Object(D.jsx)(Ye.a,{value:100,children:"50"},"100"),Object(D.jsx)(Ye.a,{value:-1,children:"ALL IN"},"-1")]})]})]})};!function(e){e.WAITING="WAITING",e.DEAL="DEAL",e.FLOP="FLOP",e.TURN="TURN",e.RIVER="RIVER",e.SHOWDOWN="SHOWDOWN",e.FINISHED="FINISHED"}(_e||(_e={}));var tt=function(){var e=Object(i.useState)(),t=Object(r.a)(e,2),a=t[0],c=t[1],s=Object(i.useState)([null,null,null,null,null,null,null,null,null,null]),l=Object(r.a)(s,2),o=l[0],d=l[1],u=Object(i.useState)([]),j=Object(r.a)(u,2),p=j[0],m=j[1],b=Object(i.useCallback)((function(e){if(e){var t,a,n=e.players.find((function(e){return e.id===x.getState().AuthState.auth.id})),i=null===e||void 0===e||null===(t=e.players)||void 0===t||null===(a=t.find((function(t){return t.id===(null===e||void 0===e?void 0:e.activePlayers[null===e||void 0===e?void 0:e.playerturn])})))||void 0===a?void 0:a.id;if(n.id!==i||n.lastAct===Me.FOLD||e.status===_e.WAITING)m([]);else switch(e.lastPlay){case Me.CHECK:m([Me.FOLD,Me.BET,Me.CHECK]);break;case Me.BET:case Me.RAISE:case Me.CALL:m([Me.FOLD,Me.CALL,Me.RAISE])}}}),[]),h=Object(i.useCallback)((function(){L.get(S.urls.localUrl+"api/game/pingGame").then((function(e){c(e.data),d(v(e.data.players)),b(e.data);var t=e.data.players.find((function(e){return e.id===x.getState().AuthState.auth.id})).wallet;x.dispatch(function(e){return{type:n.UpdateWallet,payload:e}}(t))}))}),[b]);Object(i.useEffect)((function(){h();var e=setInterval((function(){h()}),3e3);return function(){clearInterval(e)}}),[h]);var v=function(e){for(var t=[null,null,null,null,null,null,null,null,null,null],a=e.findIndex((function(e){return e.id===x.getState().AuthState.auth.id})),n=0;n<e.length-a;n++)e[a+n]&&(t[n]=new We,t[n].id=e[a+n].id,t[n].name=e[a+n].name,t[n].lastAct=e[a+n].lastAct,t[n].lastAmount=e[a+n].lastAmount,t[n].wallet=e[a+n].wallet,t[n].winner=e[a+n].winner,t[n].card1=e[a+n].card1,t[n].card2=e[a+n].card2);for(var i=0;i<a;i++)t[10-a+i]=new We,t[10-a+i].id=e[i].id,t[10-a+i].name=e[i].name,t[10-a+i].lastAct=e[i].lastAct,t[10-a+i].lastAmount=e[i].lastAmount,t[10-a+i].wallet=e[i].wallet,t[10-a+i].winner=e[i].winner,t[10-a+i].card1=e[i].card1,t[10-a+i].card2=e[i].card2;return t},O=function(e){return(null===e||void 0===e?void 0:e.id)===(null===a||void 0===a?void 0:a.activePlayers[null===a||void 0===a?void 0:a.playerturn])?" Turn":""},f=function(){var e,t=null===a||void 0===a||null===(e=a.players)||void 0===e?void 0:e.findIndex((function(e){return e.id===x.getState().AuthState.auth.id}));return(null===a||void 0===a?void 0:a.dealer)>=t?(null===a||void 0===a?void 0:a.dealer)-t:(null===a||void 0===a?void 0:a.dealer)<t?10-t+(null===a||void 0===a?void 0:a.dealer):void 0}();return Object(D.jsxs)("div",{className:"Game",children:[Object(D.jsxs)("div",{className:"PotDiv",children:["POT: ",null===a||void 0===a?void 0:a.pot]}),Object(D.jsxs)("div",{className:"Table",children:[Object(D.jsx)("div",{className:"TablePosition1",children:Object(D.jsx)(Ve,{myPosition:0,dealer:f,player:o[0],position:"CenterDown"})}),Object(D.jsx)("div",{className:"TablePosition2",children:Object(D.jsx)(Ve,{myPosition:1,dealer:f,player:o[1],position:"CenterDown"})}),Object(D.jsx)("div",{className:"TablePosition3",children:Object(D.jsx)(Ve,{myPosition:2,dealer:f,player:o[2],position:"Left"})}),Object(D.jsx)("div",{className:"TablePosition4",children:Object(D.jsx)(Ve,{myPosition:3,dealer:f,player:o[3],position:"Left"})}),Object(D.jsx)("div",{className:"TablePosition5",children:Object(D.jsx)(Ve,{myPosition:4,dealer:f,player:o[4],position:"CenterUp"})}),Object(D.jsx)("div",{className:"TablePosition6",children:Object(D.jsx)(Ve,{myPosition:5,dealer:f,player:o[5],position:"CenterUp"})}),Object(D.jsx)("div",{className:"TablePosition7",children:Object(D.jsx)(Ve,{myPosition:6,dealer:f,player:o[6],position:"CenterUp"})}),Object(D.jsx)("div",{className:"TablePosition8",children:Object(D.jsx)(Ve,{myPosition:7,dealer:f,player:o[7],position:"Right"})}),Object(D.jsx)("div",{className:"TablePosition9",children:Object(D.jsx)(Ve,{myPosition:8,dealer:f,player:o[8],position:"Right"})}),Object(D.jsx)("div",{className:"TablePosition10",children:Object(D.jsx)(Ve,{myPosition:9,dealer:f,player:o[9],position:"CenterDown"})}),a&&Object(D.jsx)(Fe,{cards:a.flop})]}),Object(D.jsx)("div",{className:"Position1"+O(o[0]),children:Object(D.jsx)(Be,{player:o[0]})}),Object(D.jsx)("div",{className:"Position2"+O(o[1]),children:Object(D.jsx)(Be,{player:o[1]})}),Object(D.jsx)("div",{className:"Position3"+O(o[2]),children:Object(D.jsx)("div",{className:"PositionUpRight",children:Object(D.jsx)(Be,{player:o[2]})})}),Object(D.jsx)("div",{className:"Position4"+O(o[3]),children:Object(D.jsx)("div",{className:"PositionDownRight",children:Object(D.jsx)(Be,{player:o[3]})})}),Object(D.jsx)("div",{className:"Position5"+O(o[4]),children:Object(D.jsx)("div",{className:"PositionDown",children:Object(D.jsx)(Be,{player:o[4]})})}),Object(D.jsx)("div",{className:"Position6"+O(o[5]),children:Object(D.jsx)("div",{className:"PositionDown",children:Object(D.jsx)(Be,{player:o[5]})})}),Object(D.jsx)("div",{className:"Position7"+O(o[6]),children:Object(D.jsx)("div",{className:"PositionDown",children:Object(D.jsx)(Be,{player:o[6]})})}),Object(D.jsx)("div",{className:"Position8"+O(o[7]),children:Object(D.jsx)("div",{className:"PositionDownLeft",children:Object(D.jsx)(Be,{player:o[7]})})}),Object(D.jsx)("div",{className:"Position9"+O(o[8]),children:Object(D.jsx)("div",{className:"PositionUpLeft",children:Object(D.jsx)(Be,{player:o[8]})})}),Object(D.jsx)("div",{className:"Position10"+O(o[9]),children:Object(D.jsx)(Be,{player:o[9]})}),Object(D.jsx)("div",{className:"Play",children:Object(D.jsx)(et,{game:a,plays:p,ping:h})})]})};a(125);var at=function(){var e=Object(i.useState)(),t=Object(r.a)(e,2),a=t[0],n=t[1],c=Object(d.g)(),s=function(){L.get(S.urls.localUrl+"/api/game/getOpenGames").then((function(e){n(e.data)})).catch((function(e){f(e)}))};return Object(i.useEffect)((function(){s()}),[]),Object(D.jsxs)(D.Fragment,{children:[Object(D.jsx)(U.a,{variant:"h5",component:"h1",children:"Select a game"}),Object(D.jsx)("br",{}),Object(D.jsxs)("div",{className:"JoinGame",children:[a&&a.map((function(e,t){return Object(D.jsx)("div",{onClick:function(){return function(e){L.post(S.urls.localUrl+"api/game/join/"+e).then((function(t){c.push("/game/"+e)})).catch((function(e){f(e),s()}))}(e)},className:"GameClass Box",children:e},t)})),Object(D.jsx)("div",{onClick:function(){c.push("/create")},className:"CreateClass Box",children:"Create New Game"})]})]})},nt=(a(126),Object(E.a)({textfieldInput:{color:"lightGray"},buttonStyle:{textTransform:"lowercase"}}));var it=function(){var e=Object(d.g)(),t=Object(R.a)(),a=t.register,n=t.handleSubmit,i=nt(),c=Object(D.jsxs)("div",{className:"LoginFormDiv",children:[Object(D.jsxs)("form",{onSubmit:n((function(t){I.a.get(S.urls.localUrl+"api/player/login",{headers:{email:t.email,password:t.password}}).then((function(t){x.dispatch(b(t.data)),t.data.currentGame?e.push("/game/"+t.data.currentGame):e.push("/join")})).catch((function(e){f(e)}))})),className:"LoginForm",children:[Object(D.jsx)(U.a,{component:"h1",variant:"h6",children:"Sign in"}),Object(D.jsx)(k.a,Object(u.a)(Object(u.a)({name:"email"},a("email")),{},{className:"TextfieldClass",InputProps:{className:i.textfieldInput},InputLabelProps:{className:i.textfieldInput},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"email",type:"email",autoFocus:!0})),Object(D.jsx)(k.a,Object(u.a)({className:"TextfieldClass",InputLabelProps:{className:i.textfieldInput},InputProps:{className:i.textfieldInput},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"password",type:"password",name:"password"},a("password"))),Object(D.jsx)("br",{}),Object(D.jsx)("br",{}),Object(D.jsx)(o.a,{type:"submit",variant:"contained",color:"secondary",children:"Submit"})]}),Object(D.jsx)(o.a,{className:i.buttonStyle,onClick:function(){e.push("/signUp")},children:"Not a player yet? SignUp"})]});return Object(D.jsxs)("div",{className:"Login",children:[Object(D.jsx)("br",{}),Object(D.jsx)("br",{}),c]})},ct=(a(127),Object(E.a)({textfieldInput:{color:"lightGray"},buttonStyle:{textTransform:"lowercase"}}));var st=function(){var e,t=Object(d.g)(),a=Object(R.a)(),n=a.register,i=a.handleSubmit,c=a.formState.errors,s=ct(),l=Object(D.jsxs)("div",{className:"SignUpFormDiv",children:[Object(D.jsxs)("form",{onSubmit:i((function(e){console.log(e),I.a.post(S.urls.localUrl+"api/player/signup",e).then((function(e){x.dispatch(b(e.data)),t.push("/join")})).catch((function(e){f(e)}))})),className:"LoginForm",children:[Object(D.jsx)(U.a,{component:"h1",variant:"h6",children:"Sign Up"}),Object(D.jsx)(k.a,Object(u.a)({className:"TextfieldClass",InputLabelProps:{className:s.textfieldInput},InputProps:{className:s.textfieldInput},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"name",name:"name",type:"text"},n("name",{minLength:{value:3,message:"please enter longer name"}}))),Object(D.jsx)("br",{}),Object(D.jsx)("span",{children:null===(e=c.name)||void 0===e?void 0:e.message}),Object(D.jsx)(k.a,Object(u.a)({className:"TextfieldClass",InputProps:{className:s.textfieldInput},InputLabelProps:{className:s.textfieldInput},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"email",type:"email",autoFocus:!0,name:"email"},n("email"))),Object(D.jsx)(k.a,Object(u.a)({className:"TextfieldClass",InputLabelProps:{className:s.textfieldInput},InputProps:{className:s.textfieldInput},variant:"outlined",margin:"normal",required:!0,fullWidth:!0,label:"password",type:"password",autoComplete:"current-password",name:"password"},n("password"))),Object(D.jsx)("br",{}),Object(D.jsx)("br",{}),Object(D.jsx)(o.a,{type:"submit",variant:"contained",color:"secondary",children:"Submit"})]}),Object(D.jsx)(o.a,{className:s.buttonStyle,onClick:function(){t.push("/login")},children:"a player already ? SignIn"})]});return Object(D.jsxs)("div",{className:"SignUp",children:[Object(D.jsx)("br",{}),Object(D.jsx)("br",{}),l]})},lt=function(){var e=Object(d.g)();return Object(D.jsxs)("div",{children:[Object(D.jsx)("br",{}),Object(D.jsx)("h2",{children:"The page you are looking for doesn't exist."}),Object(D.jsx)(o.a,{onClick:function(){e.push("/")},children:"Return to home"})]})};a(128);var rt=function(){return Object(D.jsx)("div",{className:"Routing",children:Object(D.jsxs)(d.d,{children:[Object(D.jsx)(d.b,{path:"/login",component:it,exact:!0}),Object(D.jsx)(d.b,{path:"/signUp",component:st,exact:!0}),Object(D.jsx)(d.b,{path:"/join",component:at,exact:!0}),Object(D.jsx)(d.b,{path:"/create",component:F,exact:!0}),Object(D.jsx)(d.b,{path:"/game/:game",component:tt,exact:!0}),Object(D.jsx)(d.a,{from:"/",to:"/login",exact:!0}),Object(D.jsx)(d.b,{component:lt})]})})},ot=a(168);var dt=function(){return Object(D.jsxs)(H.a,{children:[Object(D.jsx)(ot.a,{}),Object(D.jsxs)("div",{className:"Layout",children:[Object(D.jsx)("header",{children:Object(D.jsx)(T,{})}),Object(D.jsx)("main",{children:Object(D.jsx)(rt,{})}),Object(D.jsx)("footer",{})]})]})};var ut=function(){return Object(D.jsx)(dt,{})},jt=function(e){e&&e instanceof Function&&a.e(3).then(a.bind(null,175)).then((function(t){var a=t.getCLS,n=t.getFID,i=t.getFCP,c=t.getLCP,s=t.getTTFB;a(e),n(e),i(e),c(e),s(e)}))};l.a.render(Object(D.jsx)(c.a.StrictMode,{children:Object(D.jsx)(ut,{})}),document.getElementById("root")),jt()},86:function(e,t,a){},87:function(e,t,a){},88:function(e,t,a){}},[[129,1,2]]]);
//# sourceMappingURL=main.089cda57.chunk.js.map