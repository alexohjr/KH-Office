<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<title>KH 그룹웨어 로그인</title>
<!-- favicon 설정 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/icon/favicon.ico" >
<!-- 사이트 공통 css -->
<link rel = "stylesheet" type = "text/css" href ="${pageContext.request.contextPath}/commons/css/common.css">
<!-- prefix free 플러그인 -->
<link rel="stylesheet" type = "text/css" href ="${pageContext.request.contextPath}/commons/css/prefixfree.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/commons/css/login.css" />
<!-- 제이쿼리 로드 -->
<script src="${pageContext.request.contextPath }/commons/js/jquery-3.3.1.min.js"></script>
<!-- 사이트 공통 js -->
<script src="${pageContext.request.contextPath}/commons/js/common.js"></script>
<!-- 사이트 공통 유틸리티 js -->
<script src="${pageContext.request.contextPath}/commons/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/commons/js/login.js"></script>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300);
body {
  background-color: #1a2b37;
  overflow: hidden;
}

.rocket {
  position: absolute;
  top: 40%;
  margin-left: -78px;
  left: 50%;
  animation: speeder .4s linear infinite;
  z-index: 999;
}

.rocket:hover {
  animation: rotate-center 0.6s ease-in-out both;
}

@keyframes speeder {
  0% {
    transform: translate(2px, 1px) rotate(0deg);
  }
  10% {
    transform: translate(-1px, -3px) rotate(-1deg);
  }
  20% {
    transform: translate(-2px, 0px) rotate(1deg);
  }
  30% {
    transform: translate(1px, 2px) rotate(0deg);
  }
  40% {
    transform: translate(1px, -1px) rotate(1deg);
  }
  50% {
    transform: translate(-1px, 3px) rotate(-1deg);
  }
  60% {
    transform: translate(-1px, 1px) rotate(0deg);
  }
  70% {
    transform: translate(3px, 1px) rotate(-1deg);
  }
  80% {
    transform: translate(-2px, -1px) rotate(1deg);
  }
  90% {
    transform: translate(2px, 1px) rotate(0deg);
  }
  100% {
    transform: translate(1px, -2px) rotate(-1deg);
  }
}
.movingstars {
  position: absolute;
  width: 100%;
  height: 100%;
}

.movingstars span {
  position: absolute;
  height: 2px;
}

.movingstars span:nth-child(1) {
  top: 20%;
  width: 1%;
  height: 2px;
  animation: lf 4s linear infinite;
  animation-delay: -4s;
  width: 20%;
}

.movingstars span:nth-child(2) {
  top: 40%;
  width: 1%;
  height: 2px;
  animation: lf2 3s linear infinite;
  animation-delay: -1s;
}

.movingstars span:nth-child(3) {
  top: 60%;
  width: 1%;
  height: 2px;
  animation: lf3 4s linear infinite;
  animation-delay: -2s;
}

.movingstars span:nth-child(4) {
  top: 80%;
  width: 1%;
  height: 3px;
  animation: lf4 3s linear infinite;
  animation-delay: -3s;
}

.movingstars span:nth-child(5) {
  top: 40%;
  width: 5%;
  height: 4px;
  animation: lf2 2s linear infinite;
  animation-delay: -4s;
}

@keyframes lf {
  0% {
    left: 200%;
  }
  100% {
    left: -200%;
    opacity: 0;
  }
}
@keyframes lf2 {
  0% {
    left: 200%;
  }
  100% {
    left: -200%;
    opacity: 0;
  }
}
@keyframes lf3 {
  0% {
    left: 200%;
  }
  100% {
    left: -100%;
    opacity: 0;
  }
}
@keyframes lf4 {
  0% {
    left: 200%;
  }
  100% {
    left: -100%;
    opacity: 0;
  }
}

#stars {
  width: 1px;
  height: 1px;
  background: transparent;
  box-shadow: 756px 315px #FFF , 1809px 1550px #FFF , 1649px 1975px #FFF , 377px 1367px #FFF , 595px 289px #FFF , 893px 1646px #FFF , 129px 1676px #FFF , 795px 1794px #FFF , 911px 1365px #FFF , 1391px 1342px #FFF , 946px 1358px #FFF , 1473px 141px #FFF , 1419px 852px #FFF , 1461px 1224px #FFF , 432px 535px #FFF , 813px 603px #FFF , 1432px 1740px #FFF , 866px 582px #FFF , 1844px 1828px #FFF , 1522px 107px #FFF , 729px 1321px #FFF , 1202px 392px #FFF , 399px 1628px #FFF , 137px 1849px #FFF , 1171px 931px #FFF , 159px 1663px #FFF , 769px 1047px #FFF , 1259px 1159px #FFF , 1489px 486px #FFF , 135px 1874px #FFF , 1601px 34px #FFF , 531px 157px #FFF , 1239px 1968px #FFF , 149px 1924px #FFF , 982px 1714px #FFF , 1742px 1171px #FFF , 416px 910px #FFF , 498px 933px #FFF , 1102px 884px #FFF , 602px 77px #FFF , 1349px 1380px #FFF , 602px 1947px #FFF , 207px 1281px #FFF , 151px 179px #FFF , 1513px 981px #FFF , 1927px 1939px #FFF , 832px 1560px #FFF , 660px 592px #FFF , 1628px 471px #FFF , 379px 1651px #FFF , 126px 396px #FFF , 1040px 1138px #FFF , 1224px 738px #FFF , 872px 1654px #FFF , 280px 685px #FFF , 554px 954px #FFF , 1378px 147px #FFF , 1724px 1765px #FFF , 1466px 705px #FFF , 1399px 1392px #FFF , 1283px 790px #FFF , 878px 1377px #FFF , 149px 1268px #FFF , 1942px 1502px #FFF , 1443px 1987px #FFF , 1798px 1092px #FFF , 1184px 918px #FFF , 1982px 913px #FFF , 630px 1506px #FFF , 1735px 1069px #FFF , 924px 923px #FFF , 576px 1727px #FFF , 599px 1178px #FFF , 573px 36px #FFF , 156px 1941px #FFF , 1512px 498px #FFF , 621px 198px #FFF , 1608px 822px #FFF , 1159px 891px #FFF , 1554px 563px #FFF , 735px 1451px #FFF , 1862px 1927px #FFF , 406px 1844px #FFF , 1711px 1302px #FFF , 1552px 112px #FFF , 417px 4px #FFF , 1700px 1446px #FFF , 342px 1573px #FFF , 1917px 529px #FFF , 484px 141px #FFF , 1817px 4px #FFF , 1334px 756px #FFF , 90px 249px #FFF , 744px 1906px #FFF , 892px 243px #FFF , 1679px 559px #FFF , 1904px 1265px #FFF , 177px 74px #FFF , 1387px 130px #FFF , 303px 558px #FFF , 108px 1403px #FFF , 811px 1498px #FFF , 1199px 147px #FFF , 1252px 655px #FFF , 161px 289px #FFF , 887px 157px #FFF , 1329px 1092px #FFF , 11px 589px #FFF , 1206px 833px #FFF , 719px 311px #FFF , 934px 297px #FFF , 1458px 1213px #FFF , 1559px 68px #FFF , 852px 1253px #FFF , 547px 906px #FFF , 1555px 81px #FFF , 1241px 1618px #FFF , 1231px 207px #FFF , 568px 343px #FFF , 858px 1264px #FFF , 608px 711px #FFF , 1361px 1298px #FFF , 664px 813px #FFF , 1066px 332px #FFF , 1553px 778px #FFF , 1572px 658px #FFF , 737px 1963px #FFF , 1817px 405px #FFF , 347px 1598px #FFF , 1506px 936px #FFF , 1352px 1172px #FFF , 1221px 56px #FFF , 144px 1908px #FFF , 884px 641px #FFF , 233px 137px #FFF , 234px 172px #FFF , 1708px 908px #FFF , 459px 133px #FFF , 328px 1704px #FFF , 1448px 1107px #FFF , 1699px 1760px #FFF , 901px 1100px #FFF , 1629px 1573px #FFF , 1658px 183px #FFF , 1017px 673px #FFF , 946px 1929px #FFF , 721px 369px #FFF , 1107px 292px #FFF , 825px 453px #FFF , 659px 442px #FFF , 1033px 620px #FFF , 1994px 1839px #FFF , 609px 325px #FFF , 1560px 466px #FFF , 1976px 1598px #FFF , 787px 1490px #FFF , 1721px 906px #FFF , 1691px 425px #FFF , 544px 727px #FFF , 670px 1140px #FFF , 136px 829px #FFF , 1022px 497px #FFF , 349px 15px #FFF , 857px 725px #FFF , 1112px 904px #FFF , 709px 177px #FFF , 1296px 1409px #FFF , 1090px 1454px #FFF , 1727px 736px #FFF , 1923px 483px #FFF , 1659px 1419px #FFF , 612px 865px #FFF , 1619px 917px #FFF , 1935px 1009px #FFF , 1296px 1128px #FFF , 701px 290px #FFF , 1510px 666px #FFF , 537px 888px #FFF , 1045px 1042px #FFF , 1527px 281px #FFF , 1169px 1749px #FFF , 9px 1505px #FFF , 449px 407px #FFF , 83px 972px #FFF , 1407px 1344px #FFF , 270px 1012px #FFF , 10px 1079px #FFF , 658px 1997px #FFF , 996px 855px #FFF , 864px 1458px #FFF , 926px 1707px #FFF , 1407px 1542px #FFF , 1741px 1774px #FFF , 415px 1933px #FFF , 1879px 1544px #FFF , 1413px 1233px #FFF , 207px 1361px #FFF , 1133px 1156px #FFF , 94px 215px #FFF , 1029px 1098px #FFF , 184px 1929px #FFF , 1253px 149px #FFF , 1358px 395px #FFF , 193px 1169px #FFF , 589px 624px #FFF , 1254px 1712px #FFF , 551px 89px #FFF , 818px 1487px #FFF , 1648px 1131px #FFF , 1930px 826px #FFF , 1745px 400px #FFF , 708px 6px #FFF , 502px 1646px #FFF , 337px 1989px #FFF , 663px 233px #FFF , 1046px 826px #FFF , 1910px 184px #FFF , 1243px 1835px #FFF , 836px 476px #FFF , 797px 103px #FFF , 1722px 780px #FFF , 388px 187px #FFF , 2px 583px #FFF , 1791px 1136px #FFF , 491px 476px #FFF , 875px 1732px #FFF , 1527px 804px #FFF , 1656px 1054px #FFF , 896px 1924px #FFF , 234px 1926px #FFF , 1619px 977px #FFF , 1396px 1987px #FFF , 489px 1297px #FFF , 1311px 1521px #FFF , 1078px 1803px #FFF , 712px 1360px #FFF , 1539px 1899px #FFF , 1505px 987px #FFF , 186px 643px #FFF , 174px 1224px #FFF , 715px 1995px #FFF , 1161px 1180px #FFF , 243px 152px #FFF , 1621px 648px #FFF , 1761px 341px #FFF , 604px 1491px #FFF , 1434px 1717px #FFF , 180px 812px #FFF , 1716px 646px #FFF , 483px 1103px #FFF , 1518px 141px #FFF , 392px 1505px #FFF , 1296px 1490px #FFF , 156px 1504px #FFF , 1774px 1914px #FFF , 1924px 1779px #FFF , 899px 991px #FFF , 1025px 1884px #FFF , 820px 909px #FFF , 1213px 1413px #FFF , 1964px 1069px #FFF , 1578px 810px #FFF , 669px 1887px #FFF , 786px 1780px #FFF , 1505px 1046px #FFF , 1230px 417px #FFF , 406px 594px #FFF , 112px 543px #FFF , 299px 1251px #FFF , 9px 1761px #FFF , 512px 1704px #FFF , 1244px 1095px #FFF , 1916px 192px #FFF , 367px 539px #FFF , 811px 532px #FFF , 1400px 108px #FFF , 843px 1256px #FFF , 422px 1301px #FFF , 317px 1182px #FFF , 1381px 303px #FFF , 1705px 432px #FFF , 1812px 1047px #FFF , 832px 365px #FFF , 1197px 1897px #FFF , 1475px 1548px #FFF , 972px 1586px #FFF , 288px 1118px #FFF , 1834px 1780px #FFF , 429px 1235px #FFF , 1824px 1367px #FFF , 1608px 1974px #FFF , 1319px 83px #FFF , 1466px 1941px #FFF , 539px 744px #FFF , 823px 1693px #FFF , 1755px 350px #FFF , 1769px 1774px #FFF , 8px 670px #FFF , 993px 65px #FFF , 1069px 1512px #FFF , 1541px 308px #FFF , 1973px 1238px #FFF , 1392px 966px #FFF , 1777px 145px #FFF , 1593px 1493px #FFF , 399px 868px #FFF , 212px 97px #FFF , 409px 849px #FFF , 157px 1691px #FFF , 1500px 1639px #FFF , 179px 1186px #FFF , 262px 758px #FFF , 748px 807px #FFF , 428px 1837px #FFF , 260px 1961px #FFF , 1222px 548px #FFF , 617px 1765px #FFF , 246px 1929px #FFF , 1390px 734px #FFF , 338px 1814px #FFF , 1894px 1327px #FFF , 300px 215px #FFF , 1540px 1735px #FFF , 1907px 958px #FFF , 1837px 584px #FFF , 1015px 414px #FFF , 740px 1260px #FFF , 471px 1846px #FFF , 714px 424px #FFF , 1644px 540px #FFF , 1814px 1726px #FFF , 87px 523px #FFF , 1337px 1124px #FFF , 887px 993px #FFF , 720px 769px #FFF , 334px 777px #FFF , 1367px 733px #FFF , 592px 1460px #FFF , 1872px 1101px #FFF , 1398px 1284px #FFF , 455px 404px #FFF , 1364px 1057px #FFF , 543px 964px #FFF , 1048px 1672px #FFF , 1094px 1462px #FFF , 1135px 163px #FFF , 1676px 944px #FFF , 866px 540px #FFF , 1491px 1106px #FFF , 922px 1021px #FFF , 89px 336px #FFF , 161px 1187px #FFF , 1364px 426px #FFF , 1021px 13px #FFF , 1680px 1461px #FFF , 1373px 1665px #FFF , 946px 162px #FFF , 580px 289px #FFF , 1607px 1623px #FFF , 1965px 1558px #FFF , 1264px 280px #FFF , 1380px 1911px #FFF , 300px 1140px #FFF , 1073px 752px #FFF , 776px 677px #FFF , 1526px 1984px #FFF , 1422px 1698px #FFF , 874px 1850px #FFF , 1729px 1671px #FFF , 1556px 1372px #FFF , 1188px 752px #FFF , 775px 1371px #FFF , 1458px 1845px #FFF , 270px 336px #FFF , 1514px 1245px #FFF , 705px 31px #FFF , 1312px 1713px #FFF , 612px 1169px #FFF , 1103px 1791px #FFF , 1236px 1261px #FFF , 448px 1949px #FFF , 612px 476px #FFF , 969px 1542px #FFF , 630px 1006px #FFF , 1161px 309px #FFF , 915px 1202px #FFF , 1637px 1566px #FFF , 1905px 1982px #FFF , 221px 1555px #FFF , 335px 956px #FFF , 823px 1373px #FFF , 528px 1016px #FFF , 1912px 371px #FFF , 368px 1419px #FFF , 1330px 1089px #FFF , 1191px 1519px #FFF , 1992px 1510px #FFF , 1275px 1970px #FFF , 1023px 1855px #FFF , 307px 1883px #FFF , 1655px 1572px #FFF , 1981px 1860px #FFF , 712px 543px #FFF , 412px 613px #FFF , 610px 1777px #FFF , 1355px 1929px #FFF , 1822px 1660px #FFF , 890px 200px #FFF , 985px 730px #FFF , 994px 726px #FFF , 667px 18px #FFF , 1727px 847px #FFF , 1581px 150px #FFF , 888px 1973px #FFF , 396px 904px #FFF , 1984px 1214px #FFF , 1815px 1984px #FFF , 1871px 1215px #FFF , 1138px 1523px #FFF , 1916px 550px #FFF , 1257px 216px #FFF , 1116px 1750px #FFF , 1062px 998px #FFF , 128px 1193px #FFF , 140px 311px #FFF , 1530px 78px #FFF , 776px 44px #FFF , 1973px 411px #FFF , 1079px 32px #FFF , 168px 933px #FFF , 259px 1044px #FFF , 1713px 267px #FFF , 1591px 719px #FFF , 832px 1672px #FFF , 1441px 613px #FFF , 488px 1915px #FFF , 563px 1632px #FFF , 1590px 686px #FFF , 1740px 341px #FFF , 1017px 1247px #FFF , 1763px 1115px #FFF , 954px 1577px #FFF , 406px 1775px #FFF , 974px 1885px #FFF , 1956px 49px #FFF , 768px 1412px #FFF , 520px 611px #FFF , 1380px 938px #FFF , 863px 1522px #FFF , 1110px 723px #FFF , 1674px 641px #FFF , 1172px 1039px #FFF , 875px 454px #FFF , 1582px 640px #FFF , 691px 325px #FFF , 16px 82px #FFF , 1576px 1021px #FFF , 1622px 1233px #FFF , 1418px 1862px #FFF , 520px 583px #FFF , 1081px 497px #FFF , 1466px 1591px #FFF , 1233px 1135px #FFF , 1922px 612px #FFF , 406px 672px #FFF , 1621px 1998px #FFF , 1085px 762px #FFF , 83px 1887px #FFF , 1599px 1867px #FFF , 807px 885px #FFF , 1931px 1966px #FFF , 1976px 782px #FFF , 1713px 992px #FFF , 191px 605px #FFF , 710px 614px #FFF , 219px 446px #FFF , 1531px 305px #FFF , 873px 336px #FFF , 1924px 1566px #FFF , 57px 322px #FFF , 538px 1822px #FFF , 717px 1337px #FFF , 1894px 554px #FFF , 1988px 504px #FFF , 1692px 358px #FFF , 478px 609px #FFF , 1134px 96px #FFF , 1364px 991px #FFF , 1192px 410px #FFF , 888px 1414px #FFF , 734px 1445px #FFF , 907px 824px #FFF , 1733px 1574px #FFF , 638px 1136px #FFF , 755px 1968px #FFF , 349px 754px #FFF , 743px 1086px #FFF , 1288px 56px #FFF , 1334px 617px #FFF , 242px 230px #FFF , 467px 1431px #FFF , 338px 389px #FFF , 1583px 1552px #FFF , 820px 205px #FFF , 127px 121px #FFF , 1503px 1600px #FFF , 467px 825px #FFF , 1871px 1630px #FFF , 551px 450px #FFF , 961px 1704px #FFF , 1902px 1959px #FFF , 253px 1791px #FFF , 278px 822px #FFF , 872px 1405px #FFF , 939px 1477px #FFF , 536px 417px #FFF , 1997px 1772px #FFF , 1920px 1885px #FFF , 444px 499px #FFF , 1376px 1475px #FFF , 1035px 1237px #FFF , 1061px 215px #FFF , 1431px 1012px #FFF , 554px 418px #FFF , 44px 975px #FFF , 1210px 1316px #FFF , 733px 728px #FFF , 1751px 590px #FFF , 524px 917px #FFF , 1792px 991px #FFF , 1504px 68px #FFF , 1157px 698px #FFF , 1707px 1333px #FFF , 381px 563px #FFF , 272px 693px #FFF , 1853px 465px #FFF , 71px 775px #FFF , 922px 1120px #FFF , 336px 432px #FFF , 1594px 1398px #FFF , 1795px 1871px #FFF , 638px 1514px #FFF , 1439px 636px #FFF , 93px 103px #FFF , 596px 1389px #FFF , 1296px 125px #FFF , 1705px 1991px #FFF , 1246px 57px #FFF , 1185px 1788px #FFF , 973px 969px #FFF , 462px 1442px #FFF , 1762px 1400px #FFF , 1928px 467px #FFF , 1911px 1515px #FFF , 1119px 790px #FFF , 1549px 595px #FFF , 1582px 610px #FFF , 357px 1143px #FFF , 552px 1278px #FFF , 739px 1522px #FFF , 151px 22px #FFF , 582px 1706px #FFF , 1502px 581px #FFF , 433px 225px #FFF , 1675px 361px #FFF , 1317px 646px #FFF , 687px 69px #FFF , 1367px 1679px #FFF , 431px 1216px #FFF , 32px 1226px #FFF , 1721px 272px #FFF , 1576px 708px #FFF , 752px 1957px #FFF , 1266px 254px #FFF , 872px 876px #FFF , 1729px 1622px #FFF , 752px 1657px #FFF , 1709px 810px #FFF , 1622px 465px #FFF , 942px 1962px #FFF , 1930px 42px #FFF , 1921px 1381px #FFF , 994px 1964px #FFF , 1925px 567px #FFF , 1314px 1701px #FFF , 1600px 1688px #FFF , 980px 1708px #FFF , 1455px 1729px #FFF , 136px 74px #FFF , 1027px 110px #FFF , 259px 1904px #FFF , 1769px 1541px #FFF , 230px 634px #FFF , 1572px 765px #FFF , 576px 1523px #FFF , 353px 40px #FFF , 664px 1058px #FFF , 1743px 1270px #FFF , 824px 1558px #FFF , 1611px 537px #FFF , 921px 1438px #FFF , 277px 965px #FFF , 846px 787px #FFF , 386px 1065px #FFF , 1752px 195px #FFF , 144px 1267px #FFF , 217px 1107px #FFF , 453px 1023px #FFF , 1406px 1464px #FFF , 1452px 385px #FFF , 1971px 1599px #FFF , 1466px 1239px #FFF , 1063px 1700px #FFF , 1887px 1878px #FFF , 728px 1414px #FFF , 1403px 1944px #FFF , 1599px 1817px #FFF , 1807px 344px #FFF , 288px 1347px #FFF , 1738px 694px #FFF , 257px 145px #FFF , 532px 171px #FFF , 1814px 1989px #FFF , 1965px 146px #FFF , 1598px 1247px #FFF , 1551px 67px #FFF , 1887px 1443px #FFF , 754px 576px #FFF , 1361px 1432px #FFF , 308px 166px #FFF , 118px 1396px #FFF , 1149px 919px #FFF , 403px 1457px #FFF , 1496px 1521px #FFF , 1985px 1033px #FFF , 1310px 1956px #FFF , 832px 268px #FFF , 1588px 1722px #FFF , 344px 53px #FFF , 1883px 304px #FFF , 963px 1563px #FFF , 1277px 1823px #FFF , 1860px 778px #FFF , 1391px 45px #FFF , 642px 604px #FFF , 934px 1055px #FFF , 623px 1071px #FFF , 1165px 1995px #FFF , 1599px 758px #FFF , 1956px 409px #FFF , 820px 1466px #FFF , 1156px 1136px #FFF , 1368px 1738px #FFF , 987px 1407px #FFF , 1849px 178px #FFF , 1236px 1467px #FFF , 1978px 254px #FFF , 1845px 833px #FFF , 779px 861px #FFF , 1065px 1832px #FFF , 311px 970px #FFF , 1051px 396px #FFF , 253px 406px #FFF , 921px 1298px #FFF , 44px 262px #FFF , 786px 671px #FFF , 1232px 554px #FFF , 1489px 64px #FFF , 817px 1941px #FFF , 1972px 1300px #FFF , 1904px 1433px #FFF , 920px 179px #FFF , 1570px 1313px #FFF , 455px 84px #FFF , 467px 843px #FFF , 1828px 977px #FFF , 1473px 543px #FFF , 239px 925px #FFF , 622px 1700px #FFF , 686px 1188px #FFF , 1945px 1210px #FFF , 98px 1340px #FFF , 1746px 856px #FFF , 283px 1519px #FFF , 595px 272px #FFF , 1496px 1348px #FFF , 653px 1203px #FFF , 290px 4px #FFF , 1857px 874px #FFF , 771px 1434px #FFF , 668px 387px #FFF , 1972px 479px #FFF , 918px 852px #FFF , 1367px 442px #FFF , 324px 891px #FFF , 1405px 547px #FFF , 554px 602px #FFF , 155px 236px #FFF , 1845px 1353px #FFF , 1189px 1266px #FFF;
  animation: animStar 50s linear infinite;
}
#stars:after {
  content: " ";
  position: absolute;
  top: 2000px;
  width: 1px;
  height: 1px;
  background: transparent;
  box-shadow: 756px 315px #FFF , 1809px 1550px #FFF , 1649px 1975px #FFF , 377px 1367px #FFF , 595px 289px #FFF , 893px 1646px #FFF , 129px 1676px #FFF , 795px 1794px #FFF , 911px 1365px #FFF , 1391px 1342px #FFF , 946px 1358px #FFF , 1473px 141px #FFF , 1419px 852px #FFF , 1461px 1224px #FFF , 432px 535px #FFF , 813px 603px #FFF , 1432px 1740px #FFF , 866px 582px #FFF , 1844px 1828px #FFF , 1522px 107px #FFF , 729px 1321px #FFF , 1202px 392px #FFF , 399px 1628px #FFF , 137px 1849px #FFF , 1171px 931px #FFF , 159px 1663px #FFF , 769px 1047px #FFF , 1259px 1159px #FFF , 1489px 486px #FFF , 135px 1874px #FFF , 1601px 34px #FFF , 531px 157px #FFF , 1239px 1968px #FFF , 149px 1924px #FFF , 982px 1714px #FFF , 1742px 1171px #FFF , 416px 910px #FFF , 498px 933px #FFF , 1102px 884px #FFF , 602px 77px #FFF , 1349px 1380px #FFF , 602px 1947px #FFF , 207px 1281px #FFF , 151px 179px #FFF , 1513px 981px #FFF , 1927px 1939px #FFF , 832px 1560px #FFF , 660px 592px #FFF , 1628px 471px #FFF , 379px 1651px #FFF , 126px 396px #FFF , 1040px 1138px #FFF , 1224px 738px #FFF , 872px 1654px #FFF , 280px 685px #FFF , 554px 954px #FFF , 1378px 147px #FFF , 1724px 1765px #FFF , 1466px 705px #FFF , 1399px 1392px #FFF , 1283px 790px #FFF , 878px 1377px #FFF , 149px 1268px #FFF , 1942px 1502px #FFF , 1443px 1987px #FFF , 1798px 1092px #FFF , 1184px 918px #FFF , 1982px 913px #FFF , 630px 1506px #FFF , 1735px 1069px #FFF , 924px 923px #FFF , 576px 1727px #FFF , 599px 1178px #FFF , 573px 36px #FFF , 156px 1941px #FFF , 1512px 498px #FFF , 621px 198px #FFF , 1608px 822px #FFF , 1159px 891px #FFF , 1554px 563px #FFF , 735px 1451px #FFF , 1862px 1927px #FFF , 406px 1844px #FFF , 1711px 1302px #FFF , 1552px 112px #FFF , 417px 4px #FFF , 1700px 1446px #FFF , 342px 1573px #FFF , 1917px 529px #FFF , 484px 141px #FFF , 1817px 4px #FFF , 1334px 756px #FFF , 90px 249px #FFF , 744px 1906px #FFF , 892px 243px #FFF , 1679px 559px #FFF , 1904px 1265px #FFF , 177px 74px #FFF , 1387px 130px #FFF , 303px 558px #FFF , 108px 1403px #FFF , 811px 1498px #FFF , 1199px 147px #FFF , 1252px 655px #FFF , 161px 289px #FFF , 887px 157px #FFF , 1329px 1092px #FFF , 11px 589px #FFF , 1206px 833px #FFF , 719px 311px #FFF , 934px 297px #FFF , 1458px 1213px #FFF , 1559px 68px #FFF , 852px 1253px #FFF , 547px 906px #FFF , 1555px 81px #FFF , 1241px 1618px #FFF , 1231px 207px #FFF , 568px 343px #FFF , 858px 1264px #FFF , 608px 711px #FFF , 1361px 1298px #FFF , 664px 813px #FFF , 1066px 332px #FFF , 1553px 778px #FFF , 1572px 658px #FFF , 737px 1963px #FFF , 1817px 405px #FFF , 347px 1598px #FFF , 1506px 936px #FFF , 1352px 1172px #FFF , 1221px 56px #FFF , 144px 1908px #FFF , 884px 641px #FFF , 233px 137px #FFF , 234px 172px #FFF , 1708px 908px #FFF , 459px 133px #FFF , 328px 1704px #FFF , 1448px 1107px #FFF , 1699px 1760px #FFF , 901px 1100px #FFF , 1629px 1573px #FFF , 1658px 183px #FFF , 1017px 673px #FFF , 946px 1929px #FFF , 721px 369px #FFF , 1107px 292px #FFF , 825px 453px #FFF , 659px 442px #FFF , 1033px 620px #FFF , 1994px 1839px #FFF , 609px 325px #FFF , 1560px 466px #FFF , 1976px 1598px #FFF , 787px 1490px #FFF , 1721px 906px #FFF , 1691px 425px #FFF , 544px 727px #FFF , 670px 1140px #FFF , 136px 829px #FFF , 1022px 497px #FFF , 349px 15px #FFF , 857px 725px #FFF , 1112px 904px #FFF , 709px 177px #FFF , 1296px 1409px #FFF , 1090px 1454px #FFF , 1727px 736px #FFF , 1923px 483px #FFF , 1659px 1419px #FFF , 612px 865px #FFF , 1619px 917px #FFF , 1935px 1009px #FFF , 1296px 1128px #FFF , 701px 290px #FFF , 1510px 666px #FFF , 537px 888px #FFF , 1045px 1042px #FFF , 1527px 281px #FFF , 1169px 1749px #FFF , 9px 1505px #FFF , 449px 407px #FFF , 83px 972px #FFF , 1407px 1344px #FFF , 270px 1012px #FFF , 10px 1079px #FFF , 658px 1997px #FFF , 996px 855px #FFF , 864px 1458px #FFF , 926px 1707px #FFF , 1407px 1542px #FFF , 1741px 1774px #FFF , 415px 1933px #FFF , 1879px 1544px #FFF , 1413px 1233px #FFF , 207px 1361px #FFF , 1133px 1156px #FFF , 94px 215px #FFF , 1029px 1098px #FFF , 184px 1929px #FFF , 1253px 149px #FFF , 1358px 395px #FFF , 193px 1169px #FFF , 589px 624px #FFF , 1254px 1712px #FFF , 551px 89px #FFF , 818px 1487px #FFF , 1648px 1131px #FFF , 1930px 826px #FFF , 1745px 400px #FFF , 708px 6px #FFF , 502px 1646px #FFF , 337px 1989px #FFF , 663px 233px #FFF , 1046px 826px #FFF , 1910px 184px #FFF , 1243px 1835px #FFF , 836px 476px #FFF , 797px 103px #FFF , 1722px 780px #FFF , 388px 187px #FFF , 2px 583px #FFF , 1791px 1136px #FFF , 491px 476px #FFF , 875px 1732px #FFF , 1527px 804px #FFF , 1656px 1054px #FFF , 896px 1924px #FFF , 234px 1926px #FFF , 1619px 977px #FFF , 1396px 1987px #FFF , 489px 1297px #FFF , 1311px 1521px #FFF , 1078px 1803px #FFF , 712px 1360px #FFF , 1539px 1899px #FFF , 1505px 987px #FFF , 186px 643px #FFF , 174px 1224px #FFF , 715px 1995px #FFF , 1161px 1180px #FFF , 243px 152px #FFF , 1621px 648px #FFF , 1761px 341px #FFF , 604px 1491px #FFF , 1434px 1717px #FFF , 180px 812px #FFF , 1716px 646px #FFF , 483px 1103px #FFF , 1518px 141px #FFF , 392px 1505px #FFF , 1296px 1490px #FFF , 156px 1504px #FFF , 1774px 1914px #FFF , 1924px 1779px #FFF , 899px 991px #FFF , 1025px 1884px #FFF , 820px 909px #FFF , 1213px 1413px #FFF , 1964px 1069px #FFF , 1578px 810px #FFF , 669px 1887px #FFF , 786px 1780px #FFF , 1505px 1046px #FFF , 1230px 417px #FFF , 406px 594px #FFF , 112px 543px #FFF , 299px 1251px #FFF , 9px 1761px #FFF , 512px 1704px #FFF , 1244px 1095px #FFF , 1916px 192px #FFF , 367px 539px #FFF , 811px 532px #FFF , 1400px 108px #FFF , 843px 1256px #FFF , 422px 1301px #FFF , 317px 1182px #FFF , 1381px 303px #FFF , 1705px 432px #FFF , 1812px 1047px #FFF , 832px 365px #FFF , 1197px 1897px #FFF , 1475px 1548px #FFF , 972px 1586px #FFF , 288px 1118px #FFF , 1834px 1780px #FFF , 429px 1235px #FFF , 1824px 1367px #FFF , 1608px 1974px #FFF , 1319px 83px #FFF , 1466px 1941px #FFF , 539px 744px #FFF , 823px 1693px #FFF , 1755px 350px #FFF , 1769px 1774px #FFF , 8px 670px #FFF , 993px 65px #FFF , 1069px 1512px #FFF , 1541px 308px #FFF , 1973px 1238px #FFF , 1392px 966px #FFF , 1777px 145px #FFF , 1593px 1493px #FFF , 399px 868px #FFF , 212px 97px #FFF , 409px 849px #FFF , 157px 1691px #FFF , 1500px 1639px #FFF , 179px 1186px #FFF , 262px 758px #FFF , 748px 807px #FFF , 428px 1837px #FFF , 260px 1961px #FFF , 1222px 548px #FFF , 617px 1765px #FFF , 246px 1929px #FFF , 1390px 734px #FFF , 338px 1814px #FFF , 1894px 1327px #FFF , 300px 215px #FFF , 1540px 1735px #FFF , 1907px 958px #FFF , 1837px 584px #FFF , 1015px 414px #FFF , 740px 1260px #FFF , 471px 1846px #FFF , 714px 424px #FFF , 1644px 540px #FFF , 1814px 1726px #FFF , 87px 523px #FFF , 1337px 1124px #FFF , 887px 993px #FFF , 720px 769px #FFF , 334px 777px #FFF , 1367px 733px #FFF , 592px 1460px #FFF , 1872px 1101px #FFF , 1398px 1284px #FFF , 455px 404px #FFF , 1364px 1057px #FFF , 543px 964px #FFF , 1048px 1672px #FFF , 1094px 1462px #FFF , 1135px 163px #FFF , 1676px 944px #FFF , 866px 540px #FFF , 1491px 1106px #FFF , 922px 1021px #FFF , 89px 336px #FFF , 161px 1187px #FFF , 1364px 426px #FFF , 1021px 13px #FFF , 1680px 1461px #FFF , 1373px 1665px #FFF , 946px 162px #FFF , 580px 289px #FFF , 1607px 1623px #FFF , 1965px 1558px #FFF , 1264px 280px #FFF , 1380px 1911px #FFF , 300px 1140px #FFF , 1073px 752px #FFF , 776px 677px #FFF , 1526px 1984px #FFF , 1422px 1698px #FFF , 874px 1850px #FFF , 1729px 1671px #FFF , 1556px 1372px #FFF , 1188px 752px #FFF , 775px 1371px #FFF , 1458px 1845px #FFF , 270px 336px #FFF , 1514px 1245px #FFF , 705px 31px #FFF , 1312px 1713px #FFF , 612px 1169px #FFF , 1103px 1791px #FFF , 1236px 1261px #FFF , 448px 1949px #FFF , 612px 476px #FFF , 969px 1542px #FFF , 630px 1006px #FFF , 1161px 309px #FFF , 915px 1202px #FFF , 1637px 1566px #FFF , 1905px 1982px #FFF , 221px 1555px #FFF , 335px 956px #FFF , 823px 1373px #FFF , 528px 1016px #FFF , 1912px 371px #FFF , 368px 1419px #FFF , 1330px 1089px #FFF , 1191px 1519px #FFF , 1992px 1510px #FFF , 1275px 1970px #FFF , 1023px 1855px #FFF , 307px 1883px #FFF , 1655px 1572px #FFF , 1981px 1860px #FFF , 712px 543px #FFF , 412px 613px #FFF , 610px 1777px #FFF , 1355px 1929px #FFF , 1822px 1660px #FFF , 890px 200px #FFF , 985px 730px #FFF , 994px 726px #FFF , 667px 18px #FFF , 1727px 847px #FFF , 1581px 150px #FFF , 888px 1973px #FFF , 396px 904px #FFF , 1984px 1214px #FFF , 1815px 1984px #FFF , 1871px 1215px #FFF , 1138px 1523px #FFF , 1916px 550px #FFF , 1257px 216px #FFF , 1116px 1750px #FFF , 1062px 998px #FFF , 128px 1193px #FFF , 140px 311px #FFF , 1530px 78px #FFF , 776px 44px #FFF , 1973px 411px #FFF , 1079px 32px #FFF , 168px 933px #FFF , 259px 1044px #FFF , 1713px 267px #FFF , 1591px 719px #FFF , 832px 1672px #FFF , 1441px 613px #FFF , 488px 1915px #FFF , 563px 1632px #FFF , 1590px 686px #FFF , 1740px 341px #FFF , 1017px 1247px #FFF , 1763px 1115px #FFF , 954px 1577px #FFF , 406px 1775px #FFF , 974px 1885px #FFF , 1956px 49px #FFF , 768px 1412px #FFF , 520px 611px #FFF , 1380px 938px #FFF , 863px 1522px #FFF , 1110px 723px #FFF , 1674px 641px #FFF , 1172px 1039px #FFF , 875px 454px #FFF , 1582px 640px #FFF , 691px 325px #FFF , 16px 82px #FFF , 1576px 1021px #FFF , 1622px 1233px #FFF , 1418px 1862px #FFF , 520px 583px #FFF , 1081px 497px #FFF , 1466px 1591px #FFF , 1233px 1135px #FFF , 1922px 612px #FFF , 406px 672px #FFF , 1621px 1998px #FFF , 1085px 762px #FFF , 83px 1887px #FFF , 1599px 1867px #FFF , 807px 885px #FFF , 1931px 1966px #FFF , 1976px 782px #FFF , 1713px 992px #FFF , 191px 605px #FFF , 710px 614px #FFF , 219px 446px #FFF , 1531px 305px #FFF , 873px 336px #FFF , 1924px 1566px #FFF , 57px 322px #FFF , 538px 1822px #FFF , 717px 1337px #FFF , 1894px 554px #FFF , 1988px 504px #FFF , 1692px 358px #FFF , 478px 609px #FFF , 1134px 96px #FFF , 1364px 991px #FFF , 1192px 410px #FFF , 888px 1414px #FFF , 734px 1445px #FFF , 907px 824px #FFF , 1733px 1574px #FFF , 638px 1136px #FFF , 755px 1968px #FFF , 349px 754px #FFF , 743px 1086px #FFF , 1288px 56px #FFF , 1334px 617px #FFF , 242px 230px #FFF , 467px 1431px #FFF , 338px
    389px #FFF , 1583px 1552px #FFF , 820px 205px #FFF , 127px 121px #FFF , 1503px 1600px #FFF , 467px 825px #FFF , 1871px 1630px #FFF , 551px 450px #FFF , 961px 1704px #FFF , 1902px 1959px #FFF , 253px 1791px #FFF , 278px 822px #FFF , 872px 1405px #FFF , 939px 1477px #FFF , 536px 417px #FFF , 1997px 1772px #FFF , 1920px 1885px #FFF , 444px 499px #FFF , 1376px 1475px #FFF , 1035px 1237px #FFF , 1061px 215px #FFF , 1431px 1012px #FFF , 554px 418px #FFF , 44px 975px #FFF , 1210px 1316px #FFF , 733px 728px #FFF , 1751px 590px #FFF , 524px 917px #FFF , 1792px 991px #FFF , 1504px 68px #FFF , 1157px 698px #FFF , 1707px 1333px #FFF , 381px 563px #FFF , 272px 693px #FFF , 1853px 465px #FFF , 71px 775px #FFF , 922px 1120px #FFF , 336px 432px #FFF , 1594px 1398px #FFF , 1795px 1871px #FFF , 638px 1514px #FFF , 1439px 636px #FFF , 93px 103px #FFF , 596px 1389px #FFF , 1296px 125px #FFF , 1705px 1991px #FFF , 1246px 57px #FFF , 1185px 1788px #FFF , 973px 969px #FFF , 462px 1442px #FFF , 1762px 1400px #FFF , 1928px 467px #FFF , 1911px 1515px #FFF , 1119px 790px #FFF , 1549px 595px #FFF , 1582px 610px #FFF , 357px 1143px #FFF , 552px 1278px #FFF , 739px 1522px #FFF , 151px 22px #FFF , 582px 1706px #FFF , 1502px 581px #FFF , 433px 225px #FFF , 1675px 361px #FFF , 1317px 646px #FFF , 687px 69px #FFF , 1367px 1679px #FFF , 431px 1216px #FFF , 32px 1226px #FFF , 1721px 272px #FFF , 1576px 708px #FFF , 752px 1957px #FFF , 1266px 254px #FFF , 872px 876px #FFF , 1729px 1622px #FFF , 752px 1657px #FFF , 1709px 810px #FFF , 1622px 465px #FFF , 942px 1962px #FFF , 1930px 42px #FFF , 1921px 1381px #FFF , 994px 1964px #FFF , 1925px 567px #FFF , 1314px 1701px #FFF , 1600px 1688px #FFF , 980px 1708px #FFF , 1455px 1729px #FFF , 136px 74px #FFF , 1027px 110px #FFF , 259px 1904px #FFF , 1769px 1541px #FFF , 230px 634px #FFF , 1572px 765px #FFF , 576px 1523px #FFF , 353px 40px #FFF , 664px 1058px #FFF , 1743px 1270px #FFF , 824px 1558px #FFF , 1611px 537px #FFF , 921px 1438px #FFF , 277px 965px #FFF , 846px 787px #FFF , 386px 1065px #FFF , 1752px 195px #FFF , 144px 1267px #FFF , 217px 1107px #FFF , 453px 1023px #FFF , 1406px 1464px #FFF , 1452px 385px #FFF , 1971px 1599px #FFF , 1466px 1239px #FFF , 1063px 1700px #FFF , 1887px 1878px #FFF , 728px 1414px #FFF , 1403px 1944px #FFF , 1599px 1817px #FFF , 1807px 344px #FFF , 288px 1347px #FFF , 1738px 694px #FFF , 257px 145px #FFF , 532px 171px #FFF , 1814px 1989px #FFF , 1965px 146px #FFF , 1598px 1247px #FFF , 1551px 67px #FFF , 1887px 1443px #FFF , 754px 576px #FFF , 1361px 1432px #FFF , 308px 166px #FFF , 118px 1396px #FFF , 1149px 919px #FFF , 403px 1457px #FFF , 1496px 1521px #FFF , 1985px 1033px #FFF , 1310px 1956px #FFF , 832px 268px #FFF , 1588px 1722px #FFF , 344px 53px #FFF , 1883px 304px #FFF , 963px 1563px #FFF , 1277px 1823px #FFF , 1860px 778px #FFF , 1391px 45px #FFF , 642px 604px #FFF , 934px 1055px #FFF , 623px 1071px #FFF , 1165px 1995px #FFF , 1599px 758px #FFF , 1956px 409px #FFF , 820px 1466px #FFF , 1156px 1136px #FFF , 1368px 1738px #FFF , 987px 1407px #FFF , 1849px 178px #FFF , 1236px 1467px #FFF , 1978px 254px #FFF , 1845px 833px #FFF , 779px 861px #FFF , 1065px 1832px #FFF , 311px 970px #FFF , 1051px 396px #FFF , 253px 406px #FFF , 921px 1298px #FFF , 44px 262px #FFF , 786px 671px #FFF , 1232px 554px #FFF , 1489px 64px #FFF , 817px 1941px #FFF , 1972px 1300px #FFF , 1904px 1433px #FFF , 920px 179px #FFF , 1570px 1313px #FFF , 455px 84px #FFF , 467px 843px #FFF , 1828px 977px #FFF , 1473px 543px #FFF , 239px 925px #FFF , 622px 1700px #FFF , 686px 1188px #FFF , 1945px 1210px #FFF , 98px 1340px #FFF , 1746px 856px #FFF , 283px 1519px #FFF , 595px 272px #FFF , 1496px 1348px #FFF , 653px 1203px #FFF , 290px 4px #FFF , 1857px 874px #FFF , 771px 1434px #FFF , 668px 387px #FFF , 1972px 479px #FFF , 918px 852px #FFF , 1367px 442px #FFF , 324px 891px #FFF , 1405px 547px #FFF , 554px 602px #FFF , 155px 236px #FFF , 1845px 1353px #FFF , 1189px 1266px #FFF;
}

#stars2 {
  width: 2px;
  height: 2px;
  background: transparent;
  box-shadow: 1251px 1926px #FFF , 1915px 469px #FFF , 1542px 939px #FFF , 145px 541px #FFF , 1984px 100px #FFF , 1644px 1013px #FFF , 1731px 110px #FFF , 382px 1391px #FFF , 515px 1858px #FFF , 455px 475px #FFF , 1094px 1460px #FFF , 1614px 1693px #FFF , 1944px 1020px #FFF , 363px 1948px #FFF , 423px 638px #FFF , 726px 1600px #FFF , 1793px 20px #FFF , 1175px 825px #FFF , 1354px 1433px #FFF , 118px 1161px #FFF , 1989px 1829px #FFF , 1131px 1982px #FFF , 305px 1023px #FFF , 1590px 843px #FFF , 1784px 1737px #FFF , 1964px 1175px #FFF , 343px 1992px #FFF , 553px 122px #FFF , 1891px 1641px #FFF , 1005px 253px #FFF , 1505px 52px #FFF , 623px 86px #FFF , 1266px 223px #FFF , 472px 85px #FFF , 879px 1458px #FFF , 899px 418px #FFF , 1705px 1036px #FFF , 752px 585px #FFF , 1341px 622px #FFF , 837px 1107px #FFF , 461px 1293px #FFF , 24px 506px #FFF , 1929px 788px #FFF , 370px 1516px #FFF , 141px 1132px #FFF , 783px 1268px #FFF , 622px 385px #FFF , 1707px 765px #FFF , 609px 907px #FFF , 1100px 895px #FFF , 937px 869px #FFF , 447px 816px #FFF , 781px 209px #FFF , 1940px 652px #FFF , 1951px 116px #FFF , 976px 468px #FFF , 1813px 866px #FFF , 1345px 541px #FFF , 2px 353px #FFF , 27px 1224px #FFF , 31px 558px #FFF , 970px 1402px #FFF , 326px 1159px #FFF , 1372px 76px #FFF , 1987px 1530px #FFF , 1415px 1805px #FFF , 1739px 1182px #FFF , 269px 715px #FFF , 1123px 584px #FFF , 1828px 1614px #FFF , 226px 964px #FFF , 796px 406px #FFF , 889px 446px #FFF , 1781px 386px #FFF , 1201px 1197px #FFF , 238px 756px #FFF , 1978px 892px #FFF , 673px 1228px #FFF , 525px 247px #FFF , 696px 1086px #FFF , 184px 1933px #FFF , 199px 1235px #FFF , 291px 1292px #FFF , 481px 1188px #FFF , 1534px 98px #FFF , 149px 1437px #FFF , 790px 1348px #FFF , 787px 394px #FFF , 745px 1739px #FFF , 1331px 969px #FFF , 169px 808px #FFF , 1840px 1698px #FFF , 1020px 1753px #FFF , 5px 815px #FFF , 1029px 1295px #FFF , 1607px 886px #FFF , 587px 1428px #FFF , 1261px 1597px #FFF , 162px 405px #FFF , 23px 894px #FFF , 878px 1603px #FFF , 817px 1078px #FFF , 1535px 1686px #FFF , 137px 463px #FFF , 1631px 117px #FFF , 317px 1103px #FFF , 356px 1315px #FFF , 1277px 1801px #FFF , 1748px 232px #FFF , 322px 1286px #FFF , 1483px 1370px #FFF , 1642px 347px #FFF , 300px 343px #FFF , 1375px 1941px #FFF , 61px 1252px #FFF , 1436px 1616px #FFF , 537px 954px #FFF , 589px 483px #FFF , 1828px 1519px #FFF , 1298px 139px #FFF , 102px 849px #FFF , 1036px 1019px #FFF , 353px 1635px #FFF , 872px 1722px #FFF , 392px 308px #FFF , 726px 67px #FFF , 1424px 1049px #FFF , 1816px 21px #FFF , 199px 1775px #FFF , 280px 698px #FFF , 852px 1951px #FFF , 1878px 475px #FFF , 1947px 1984px #FFF , 1709px 1217px #FFF , 1973px 1719px #FFF , 639px 212px #FFF , 839px 1011px #FFF , 787px 189px #FFF , 300px 441px #FFF , 20px 311px #FFF , 1978px 876px #FFF , 817px 497px #FFF , 553px 1016px #FFF , 163px 138px #FFF , 1697px 220px #FFF , 1352px 832px #FFF , 220px 1454px #FFF , 1992px 1384px #FFF , 281px 1141px #FFF , 1024px 67px #FFF , 1287px 842px #FFF , 1009px 1676px #FFF , 1177px 1079px #FFF , 118px 1765px #FFF , 1745px 930px #FFF , 1196px 220px #FFF , 1448px 1648px #FFF , 62px 909px #FFF , 1559px 1925px #FFF , 1609px 816px #FFF , 1674px 893px #FFF , 392px 162px #FFF , 1120px 456px #FFF , 873px 811px #FFF , 389px 1454px #FFF , 601px 1364px #FFF , 321px 1998px #FFF , 351px 989px #FFF , 56px 399px #FFF , 695px 1206px #FFF , 377px 1437px #FFF , 760px 988px #FFF , 1590px 1335px #FFF , 1782px 1102px #FFF , 931px 634px #FFF , 1656px 1228px #FFF , 655px 1701px #FFF , 1753px 663px #FFF , 1353px 252px #FFF , 1009px 699px #FFF , 425px 54px #FFF , 1910px 1249px #FFF , 183px 467px #FFF , 1581px 115px #FFF , 603px 388px #FFF , 198px 310px #FFF , 828px 751px #FFF , 1912px 1914px #FFF , 563px 445px #FFF , 1390px 74px #FFF , 1316px 1789px #FFF , 1033px 1106px #FFF , 248px 977px #FFF , 364px 555px #FFF , 1374px 394px #FFF , 526px 507px #FFF , 254px 1804px #FFF , 571px 546px #FFF , 82px 1101px #FFF , 1001px 1935px #FFF;
  animation: animStar 100s linear infinite;
}
#stars2:after {
  content: " ";
  position: absolute;
  top: 2000px;
  width: 2px;
  height: 2px;
  background: transparent;
  box-shadow: 1251px 1926px #FFF , 1915px 469px #FFF , 1542px 939px #FFF , 145px 541px #FFF , 1984px 100px #FFF , 1644px 1013px #FFF , 1731px 110px #FFF , 382px 1391px #FFF , 515px 1858px #FFF , 455px 475px #FFF , 1094px 1460px #FFF , 1614px 1693px #FFF , 1944px 1020px #FFF , 363px 1948px #FFF , 423px 638px #FFF , 726px 1600px #FFF , 1793px 20px #FFF , 1175px 825px #FFF , 1354px 1433px #FFF , 118px 1161px #FFF , 1989px 1829px #FFF , 1131px 1982px #FFF , 305px 1023px #FFF , 1590px 843px #FFF , 1784px 1737px #FFF , 1964px 1175px #FFF , 343px 1992px #FFF , 553px 122px #FFF , 1891px 1641px #FFF , 1005px 253px #FFF , 1505px 52px #FFF , 623px 86px #FFF , 1266px 223px #FFF , 472px 85px #FFF , 879px 1458px #FFF , 899px 418px #FFF , 1705px 1036px #FFF , 752px 585px #FFF , 1341px 622px #FFF , 837px 1107px #FFF , 461px 1293px #FFF , 24px 506px #FFF , 1929px 788px #FFF , 370px 1516px #FFF , 141px 1132px #FFF , 783px 1268px #FFF , 622px 385px #FFF , 1707px 765px #FFF , 609px 907px #FFF , 1100px 895px #FFF , 937px 869px #FFF , 447px 816px #FFF , 781px 209px #FFF , 1940px 652px #FFF , 1951px 116px #FFF , 976px 468px #FFF , 1813px 866px #FFF , 1345px 541px #FFF , 2px 353px #FFF , 27px 1224px #FFF , 31px 558px #FFF , 970px 1402px #FFF , 326px 1159px #FFF , 1372px 76px #FFF , 1987px 1530px #FFF , 1415px 1805px #FFF , 1739px 1182px #FFF , 269px 715px #FFF , 1123px 584px #FFF , 1828px 1614px #FFF , 226px 964px #FFF , 796px 406px #FFF , 889px 446px #FFF , 1781px 386px #FFF , 1201px 1197px #FFF , 238px 756px #FFF , 1978px 892px #FFF , 673px 1228px #FFF , 525px 247px #FFF , 696px 1086px #FFF , 184px 1933px #FFF , 199px 1235px #FFF , 291px 1292px #FFF , 481px 1188px #FFF , 1534px 98px #FFF , 149px 1437px #FFF , 790px 1348px #FFF , 787px 394px #FFF , 745px 1739px #FFF , 1331px 969px #FFF , 169px 808px #FFF , 1840px 1698px #FFF , 1020px 1753px #FFF , 5px 815px #FFF , 1029px 1295px #FFF , 1607px 886px #FFF , 587px 1428px #FFF , 1261px 1597px #FFF , 162px 405px #FFF , 23px 894px #FFF , 878px 1603px #FFF , 817px 1078px #FFF , 1535px 1686px #FFF , 137px 463px #FFF , 1631px 117px #FFF , 317px 1103px #FFF , 356px 1315px #FFF , 1277px 1801px #FFF , 1748px 232px #FFF , 322px 1286px #FFF , 1483px 1370px #FFF , 1642px 347px #FFF , 300px 343px #FFF , 1375px 1941px #FFF , 61px 1252px #FFF , 1436px 1616px #FFF , 537px 954px #FFF , 589px 483px #FFF , 1828px 1519px #FFF , 1298px 139px #FFF , 102px 849px #FFF , 1036px 1019px #FFF , 353px 1635px #FFF , 872px 1722px #FFF , 392px 308px #FFF , 726px 67px #FFF , 1424px 1049px #FFF , 1816px 21px #FFF , 199px 1775px #FFF , 280px 698px #FFF , 852px 1951px #FFF , 1878px 475px #FFF , 1947px 1984px #FFF , 1709px 1217px #FFF , 1973px 1719px #FFF , 639px 212px #FFF , 839px 1011px #FFF , 787px 189px #FFF , 300px 441px #FFF , 20px 311px #FFF , 1978px 876px #FFF , 817px 497px #FFF , 553px 1016px #FFF , 163px 138px #FFF , 1697px 220px #FFF , 1352px 832px #FFF , 220px 1454px #FFF , 1992px 1384px #FFF , 281px 1141px #FFF , 1024px 67px #FFF , 1287px 842px #FFF , 1009px 1676px #FFF , 1177px 1079px #FFF , 118px 1765px #FFF , 1745px 930px #FFF , 1196px 220px #FFF , 1448px 1648px #FFF , 62px 909px #FFF , 1559px 1925px #FFF , 1609px 816px #FFF , 1674px 893px #FFF , 392px 162px #FFF , 1120px 456px #FFF , 873px 811px #FFF , 389px 1454px #FFF , 601px 1364px #FFF , 321px 1998px #FFF , 351px 989px #FFF , 56px 399px #FFF , 695px 1206px #FFF , 377px 1437px #FFF , 760px 988px #FFF , 1590px 1335px #FFF , 1782px 1102px #FFF , 931px 634px #FFF , 1656px 1228px #FFF , 655px 1701px #FFF , 1753px 663px #FFF , 1353px 252px #FFF , 1009px 699px #FFF , 425px 54px #FFF , 1910px 1249px #FFF , 183px 467px #FFF , 1581px 115px #FFF , 603px 388px #FFF , 198px 310px #FFF , 828px 751px #FFF , 1912px 1914px #FFF , 563px 445px #FFF , 1390px 74px #FFF , 1316px 1789px #FFF , 1033px 1106px #FFF , 248px 977px #FFF , 364px 555px #FFF , 1374px 394px #FFF , 526px 507px #FFF , 254px 1804px #FFF , 571px 546px #FFF , 82px 1101px #FFF , 1001px 1935px #FFF;
}

#stars3 {
  width: 3px;
  height: 3px;
  background: transparent;
  box-shadow: 1898px 602px #FFF , 1997px 1091px #FFF , 193px 1450px #FFF , 479px 944px #FFF , 376px 1179px #FFF , 217px 1668px #FFF , 326px 212px #FFF , 378px 1072px #FFF , 655px 1686px #FFF , 1481px 946px #FFF , 1150px 1432px #FFF , 207px 1744px #FFF , 1860px 220px #FFF , 1183px 1642px #FFF , 1549px 1720px #FFF , 1538px 1962px #FFF , 1923px 312px #FFF , 40px 1742px #FFF , 1901px 50px #FFF , 1938px 1524px #FFF , 1063px 171px #FFF , 484px 1609px #FFF , 1219px 907px #FFF , 1593px 1544px #FFF , 1471px 788px #FFF , 384px 1670px #FFF , 1830px 1123px #FFF , 1000px 1292px #FFF , 1283px 1690px #FFF , 796px 1073px #FFF , 414px 368px #FFF , 1395px 1454px #FFF , 695px 600px #FFF , 1218px 1190px #FFF , 1990px 617px #FFF , 140px 319px #FFF , 673px 1885px #FFF , 1769px 1777px #FFF , 1778px 751px #FFF , 1610px 335px #FFF , 1540px 116px #FFF , 1902px 1701px #FFF , 1407px 1195px #FFF , 752px 1375px #FFF , 1451px 1244px #FFF , 317px 1808px #FFF , 46px 430px #FFF , 1017px 181px #FFF , 28px 316px #FFF , 268px 1647px #FFF , 1699px 815px #FFF , 1875px 1741px #FFF , 891px 400px #FFF , 1229px 1146px #FFF , 932px 115px #FFF , 984px 796px #FFF , 655px 515px #FFF , 1230px 1763px #FFF , 1565px 1090px #FFF , 1866px 1881px #FFF , 1619px 39px #FFF , 63px 1241px #FFF , 1127px 1550px #FFF , 1613px 675px #FFF , 676px 376px #FFF , 1518px 1660px #FFF , 101px 861px #FFF , 841px 1680px #FFF , 1951px 1416px #FFF , 414px 325px #FFF , 900px 988px #FFF , 699px 1882px #FFF , 1769px 1572px #FFF , 564px 1231px #FFF , 1659px 1299px #FFF , 48px 1150px #FFF , 1178px 277px #FFF , 125px 286px #FFF , 1322px 847px #FFF , 643px 554px #FFF , 1075px 1748px #FFF , 1468px 830px #FFF , 481px 78px #FFF , 1163px 230px #FFF , 1901px 658px #FFF , 1917px 1398px #FFF , 842px 1027px #FFF , 613px 812px #FFF , 975px 409px #FFF , 606px 278px #FFF , 1724px 17px #FFF , 1914px 1606px #FFF , 1642px 1155px #FFF , 1046px 357px #FFF , 1332px 1697px #FFF , 212px 1092px #FFF , 502px 935px #FFF , 1660px 658px #FFF , 1232px 880px #FFF , 507px 1598px #FFF;
  animation: animStar 150s linear infinite;
}
#stars3:after {
  content: " ";
  position: absolute;
  top: 2000px;
  width: 3px;
  height: 3px;
  background: transparent;
  box-shadow: 1898px 602px #FFF , 1997px 1091px #FFF , 193px 1450px #FFF , 479px 944px #FFF , 376px 1179px #FFF , 217px 1668px #FFF , 326px 212px #FFF , 378px 1072px #FFF , 655px 1686px #FFF , 1481px 946px #FFF , 1150px 1432px #FFF , 207px 1744px #FFF , 1860px 220px #FFF , 1183px 1642px #FFF , 1549px 1720px #FFF , 1538px 1962px #FFF , 1923px 312px #FFF , 40px 1742px #FFF , 1901px 50px #FFF , 1938px 1524px #FFF , 1063px 171px #FFF , 484px 1609px #FFF , 1219px 907px #FFF , 1593px 1544px #FFF , 1471px 788px #FFF , 384px 1670px #FFF , 1830px 1123px #FFF , 1000px 1292px #FFF , 1283px 1690px #FFF , 796px 1073px #FFF , 414px 368px #FFF , 1395px 1454px #FFF , 695px 600px #FFF , 1218px 1190px #FFF , 1990px 617px #FFF , 140px 319px #FFF , 673px 1885px #FFF , 1769px 1777px #FFF , 1778px 751px #FFF , 1610px 335px #FFF , 1540px 116px #FFF , 1902px 1701px #FFF , 1407px 1195px #FFF , 752px 1375px #FFF , 1451px 1244px #FFF , 317px 1808px #FFF , 46px 430px #FFF , 1017px 181px #FFF , 28px 316px #FFF , 268px 1647px #FFF , 1699px 815px #FFF , 1875px 1741px #FFF , 891px 400px #FFF , 1229px 1146px #FFF , 932px 115px #FFF , 984px 796px #FFF , 655px 515px #FFF , 1230px 1763px #FFF , 1565px 1090px #FFF , 1866px 1881px #FFF , 1619px 39px #FFF , 63px 1241px #FFF , 1127px 1550px #FFF , 1613px 675px #FFF , 676px 376px #FFF , 1518px 1660px #FFF , 101px 861px #FFF , 841px 1680px #FFF , 1951px 1416px #FFF , 414px 325px #FFF , 900px 988px #FFF , 699px 1882px #FFF , 1769px 1572px #FFF , 564px 1231px #FFF , 1659px 1299px #FFF , 48px 1150px #FFF , 1178px 277px #FFF , 125px 286px #FFF , 1322px 847px #FFF , 643px 554px #FFF , 1075px 1748px #FFF , 1468px 830px #FFF , 481px 78px #FFF , 1163px 230px #FFF , 1901px 658px #FFF , 1917px 1398px #FFF , 842px 1027px #FFF , 613px 812px #FFF , 975px 409px #FFF , 606px 278px #FFF , 1724px 17px #FFF , 1914px 1606px #FFF , 1642px 1155px #FFF , 1046px 357px #FFF , 1332px 1697px #FFF , 212px 1092px #FFF , 502px 935px #FFF , 1660px 658px #FFF , 1232px 880px #FFF , 507px 1598px #FFF;
}

@keyframes animStar {
  from {
    transform: translateY(0px);
  }
  to {
    transform: translateY(-2000px);
  }
}

.stars {
    -ms-transform: rotate(-90deg); /* IE 9 */
    -webkit-transform: rotate(-90deg); /* Safari */
    transform: rotate(-90deg);
}

.movingstars svg {
    -ms-transform: rotate(180deg); /* IE 9 */
    -webkit-transform: rotate180deg); /* Safari */
    transform: rotate(180deg);
}

.phrases {
  position: absolute;
  left: 43%;
  margin-left: -28px;
  top: 70%;
  width:1000px;
}

#phrases {
  -webkit-animation: slide-phrases-upward 20s;
          animation: slide-phrases-upward 20s;
}



@-webkit-keyframes slide-phrases-upward {
  0% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
  }
  5% {
    -webkit-transform: translateY(-50px);
            transform: translateY(-50px);
  }
  10% {
    -webkit-transform: translateY(-100px);
            transform: translateY(-100px);
  }
  15% {
    -webkit-transform: translateY(-150px);
            transform: translateY(-150px);
  }
  20% {
    -webkit-transform: translateY(-200px);
            transform: translateY(-200px);
  }
  25% {
    -webkit-transform: translateY(-250px);
            transform: translateY(-250px);
  }
  30% {
    -webkit-transform: translateY(-300px);
            transform: translateY(-300px);
  }
  35% {
    -webkit-transform: translateY(-350px);
            transform: translateY(-350px);
  }
  40% {
    -webkit-transform: translateY(-400px);
            transform: translateY(-400px);
  }
  45% {
    -webkit-transform: translateY(-450px);
            transform: translateY(-450px);
  }
  50% {
    -webkit-transform: translateY(-500px);
            transform: translateY(-500px);
  }
  55% {
    -webkit-transform: translateY(-550px);
            transform: translateY(-550px);
  }
  60% {
    -webkit-transform: translateY(-600px);
            transform: translateY(-600px);
  }
  65% {
    -webkit-transform: translateY(-650px);
            transform: translateY(-650px);
  }
  70% {
    -webkit-transform: translateY(-700px);
            transform: translateY(-700px);
  }
  75% {
    -webkit-transform: translateY(-750px);
            transform: translateY(-750px);
  }
  80% {
    -webkit-transform: translateY(-800px);
            transform: translateY(-800px);
  }
  85% {
    -webkit-transform: translateY(-850px);
            transform: translateY(-850px);
  }
  90% {
    -webkit-transform: translateY(-900px);
            transform: translateY(-900px);
  }
  95% {
    -webkit-transform: translateY(-950px);
            transform: translateY(-950px);
  }
  100% {
    -webkit-transform: translateY(-1000px);
            transform: translateY(-1000px);
  }
}

@keyframes slide-phrases-upward {
  0% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
  }
  5% {
    -webkit-transform: translateY(-50px);
            transform: translateY(-50px);
  }
  10% {
    -webkit-transform: translateY(-100px);
            transform: translateY(-100px);
  }
  15% {
    -webkit-transform: translateY(-150px);
            transform: translateY(-150px);
  }
  20% {
    -webkit-transform: translateY(-200px);
            transform: translateY(-200px);
  }
  25% {
    -webkit-transform: translateY(-250px);
            transform: translateY(-250px);
  }
  30% {
    -webkit-transform: translateY(-300px);
            transform: translateY(-300px);
  }
  35% {
    -webkit-transform: translateY(-350px);
            transform: translateY(-350px);
  }
  40% {
    -webkit-transform: translateY(-400px);
            transform: translateY(-400px);
  }
  45% {
    -webkit-transform: translateY(-450px);
            transform: translateY(-450px);
  }
  50% {
    -webkit-transform: translateY(-500px);
            transform: translateY(-500px);
  }
  55% {
    -webkit-transform: translateY(-550px);
            transform: translateY(-550px);
  }
  60% {
    -webkit-transform: translateY(-600px);
            transform: translateY(-600px);
  }
  65% {
    -webkit-transform: translateY(-650px);
            transform: translateY(-650px);
  }
  70% {
    -webkit-transform: translateY(-700px);
            transform: translateY(-700px);
  }
  75% {
    -webkit-transform: translateY(-750px);
            transform: translateY(-750px);
  }
  80% {
    -webkit-transform: translateY(-800px);
            transform: translateY(-800px);
  }
  85% {
    -webkit-transform: translateY(-850px);
            transform: translateY(-850px);
  }
  90% {
    -webkit-transform: translateY(-900px);
            transform: translateY(-900px);
  }
  95% {
    -webkit-transform: translateY(-950px);
            transform: translateY(-950px);
  }
  100% {
    -webkit-transform: translateY(-1000px);
            transform: translateY(-1000px);
  }
}
#loadingCheckCircleSVG-0 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: -1.5s;
          animation-delay: -1.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-1 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: -0.5s;
          animation-delay: -0.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-2 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 0.5s;
          animation-delay: 0.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-3 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 1.5s;
          animation-delay: 1.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-4 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 2.5s;
          animation-delay: 2.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-5 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 3.5s;
          animation-delay: 3.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-6 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 4.5s;
          animation-delay: 4.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-7 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 5.5s;
          animation-delay: 5.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-8 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 6.5s;
          animation-delay: 6.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-9 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 7.5s;
          animation-delay: 7.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-10 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 8.5s;
          animation-delay: 8.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-11 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 9.5s;
          animation-delay: 9.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-12 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 10.5s;
          animation-delay: 10.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-13 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 11.5s;
          animation-delay: 11.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-14 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 12.5s;
          animation-delay: 12.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-15 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 13.5s;
          animation-delay: 13.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-16 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 14.5s;
          animation-delay: 14.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-17 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 15.5s;
          animation-delay: 15.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-18 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 16.5s;
          animation-delay: 16.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-19 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 17.5s;
          animation-delay: 17.5s;
  fill: white;
  opacity: 0;
}

#loadingCheckCircleSVG-20 {
  -webkit-animation: fill-to-white 5000ms;
          animation: fill-to-white 5000ms;
  -webkit-animation-delay: 18.5s;
          animation-delay: 18.5s;
  fill: white;
  opacity: 0;
}

@-webkit-keyframes fill-to-white {
  0% {
    opacity: 0;
  }
  10% {
    opacity: 1;
  }
  100% {
    opacity: 1;
  }
}

@keyframes fill-to-white {
  0% {
    opacity: 0;
  }
  10% {
    opacity: 1;
  }
  100% {
    opacity: 1;
  }
}
#loadingCheckSVG-0 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: -1.5s;
          animation-delay: -1.5s;
}

#loadingCheckSVG-1 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: -0.5s;
          animation-delay: -0.5s;
}

#loadingCheckSVG-2 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 0.5s;
          animation-delay: 0.5s;
}

#loadingCheckSVG-3 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 1.5s;
          animation-delay: 1.5s;
}

#loadingCheckSVG-4 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 2.5s;
          animation-delay: 2.5s;
}

#loadingCheckSVG-5 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 3.5s;
          animation-delay: 3.5s;
}

#loadingCheckSVG-6 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 4.5s;
          animation-delay: 4.5s;
}

#loadingCheckSVG-7 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 5.5s;
          animation-delay: 5.5s;
}

#loadingCheckSVG-8 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 6.5s;
          animation-delay: 6.5s;
}

#loadingCheckSVG-9 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 7.5s;
          animation-delay: 7.5s;
}

#loadingCheckSVG-10 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 8.5s;
          animation-delay: 8.5s;
}

#loadingCheckSVG-11 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 9.5s;
          animation-delay: 9.5s;
}

#loadingCheckSVG-12 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 10.5s;
          animation-delay: 10.5s;
}

#loadingCheckSVG-13 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 11.5s;
          animation-delay: 11.5s;
}

#loadingCheckSVG-14 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 12.5s;
          animation-delay: 12.5s;
}

#loadingCheckSVG-15 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 13.5s;
          animation-delay: 13.5s;
}

#loadingCheckSVG-16 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 14.5s;
          animation-delay: 14.5s;
}

#loadingCheckSVG-17 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 15.5s;
          animation-delay: 15.5s;
}

#loadingCheckSVG-18 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 16.5s;
          animation-delay: 16.5s;
}

#loadingCheckSVG-19 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 17.5s;
          animation-delay: 17.5s;
}

#loadingCheckSVG-20 {
  -webkit-animation: fill-to-coral 5000ms;
          animation: fill-to-coral 5000ms;
  -webkit-animation-delay: 18.5s;
          animation-delay: 18.5s;
}

@-webkit-keyframes fill-to-coral {
  0% {
    fill: white;
  }
  10% {
    fill: #FF6D92;
  }
  100% {
    fill: #FF6D92;
  }
}

@keyframes fill-to-coral {
  0% {
    fill: white;
  }
  10% {
    fill: #FF6D92;
  }
  100% {
    fill: #FF6D92;
  }
}

@keyframes rotate-center {
  0% {
    -webkit-transform: rotate(0);
            transform: rotate(0);
  }
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg);
  }
}
</style>
<script>
$(function(){
	setTimeout(function() {
		javascript:history.go(0);
		}, 20000);
});
</script>
<script>
var checkmarkIdPrefix = "loadingCheckSVG-";
var checkmarkCircleIdPrefix = "loadingCheckCircleSVG-";
var verticalSpacing = 50;

function shuffleArray(array) {
/*     for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    } */
    return array;
}

function createSVG(tag, properties, opt_children) {
  var newElement = document.createElementNS("http://www.w3.org/2000/svg", tag);
  for(prop in properties) {
    newElement.setAttribute(prop, properties[prop]);
  }
  if (opt_children) {
    opt_children.forEach(function(child) {
      newElement.appendChild(child);
    })
  }
  return newElement;
}

function createPhraseSvg(phrase, yOffset) {
  var text = createSVG("text", {
    fill: "white",
    x: 50,
    y: yOffset,
    "font-size": 18,
    "font-family": "Arial"
  });
  text.appendChild(document.createTextNode(phrase));
  return text;
}
function createCheckSvg(yOffset, index) {
  var check = createSVG("polygon", {
    points: "21.661,7.643 13.396,19.328 9.429,15.361 7.075,17.714 13.745,24.384 24.345,9.708 ",
    fill: "rgba(255,255,255,1)",
    id: checkmarkIdPrefix + index
  });
  var circle_outline = createSVG("path", {
    d: "M16,0C7.163,0,0,7.163,0,16s7.163,16,16,16s16-7.163,16-16S24.837,0,16,0z M16,30C8.28,30,2,23.72,2,16C2,8.28,8.28,2,16,2 c7.72,0,14,6.28,14,14C30,23.72,23.72,30,16,30z",
    fill: "white"
  })
  var circle = createSVG("circle", {
    id: checkmarkCircleIdPrefix + index,
    fill: "rgba(255,255,255,0)",
    cx: 16,
    cy: 16,
    r: 15
  })
  var group = createSVG("g", {
    transform: "translate(10 " + (yOffset - 20) + ") scale(.9)"
  }, [circle, check, circle_outline]);
  return group;
}

function addPhrasesToDocument(phrases) {
  phrases.forEach(function(phrase, index) {
    var yOffset = 30 + verticalSpacing * index;
    document.getElementById("phrases").appendChild(createPhraseSvg(phrase, yOffset));
    document.getElementById("phrases").appendChild(createCheckSvg(yOffset, index));
  });
}

function easeInOut(t) {
  var period = 200;
  return (Math.sin(t / period + 100) + 1) /2;
}

document.addEventListener("DOMContentLoaded", function(event) {
<!-- 여기에 원하는 데이터 넣으면 됩니다. -->
  var phrases = shuffleArray(["학원:KH-정보교육원","담당선생님:이수진선생님","팀명:일조",
	  "프로젝트명:KH-Office", "조장:채세종","팀원:김호","팀원:박세영","팀원:최진주", "팀원:정은솔","팀원:오창환",
	  "김 호 - 프로젝트 하는 내내 많은걸 배울수 있는 기회였고 끝까지 잘마무리해서 좋았고 다들 고생했다!!",
	  "채세종 - 팀원들 모두 깊은 이해도를 가지고 서로 도우면서 프로젝트를 마무리할 수 있어서 참 고마웠다.",
	  "박세영 - 팀원들이 잘 해주어서 의지가 많이 됐습니다. 팀웍, 팀 분위기 다 최고의 팀이었어요!",
	  "최진주 - 프로젝트 진행하면서 막힐 때마다 서로 묻고 도와주는 과정에서 많이 배울 수 있었습니다.",
	  "우리 팀원들 그동안 고생 많았어요!",
	  "정은솔 - 아직 모자란 게 많아 끝까지 할 수 있을까 싶었지만",
	  "마지막 프로젝트까지 무사히 마칠 수 있게 도와준 팀원들에게 고맙고 좋은 시간이었습니다.",
	  "오창환 - 재미있었고 뜻 깊은 시간이었습니다",
	  "수고하셨습니다~~~~~~~~~~~~~~~~~~"]);
  addPhrasesToDocument(phrases);
  var start_time = new Date().getTime();
  var upward_moving_group = document.getElementById("phrases");
  upward_moving_group.currentY = 0;
  var checks = phrases.map(function(_, i) { 
    return {check: document.getElementById(checkmarkIdPrefix + i), circle: document.getElementById(checkmarkCircleIdPrefix + i)};
  });
  function animateLoading() {
    var now = new Date().getTime();
    upward_moving_group.setAttribute("transform", "translate(0 " + upward_moving_group.currentY + ")");
    upward_moving_group.currentY -= 1.35 * easeInOut(now);
    checks.forEach(function(check, i) {
      var color_change_boundary = - i * verticalSpacing + verticalSpacing + 15;
      if (upward_moving_group.currentY < color_change_boundary) {
        var alpha = Math.max(Math.min(1 - (upward_moving_group.currentY - color_change_boundary + 15)/30, 1), 0);
        check.circle.setAttribute("fill", "rgba(255, 255, 255, " + alpha + ")");
        var check_color = [Math.round(255 * (1-alpha) + 120 * alpha), Math.round(255 * (1-alpha) + 154 * alpha)];
        check.check.setAttribute("fill", "rgba(255, " + check_color[0] + "," + check_color[1] + ", 1)");
      }
    })
    if (now - start_time < 30000 && upward_moving_group.currentY > -710) {
      requestAnimationFrame(animateLoading);
    }
  }
});
</script>
<body>
<div class="stars">
  <div id='stars'></div>
  <div id='stars2'></div>
  <div id='stars3'></div>
</div>

<div class='rocket'>

<svg xmlns="http://www.w3.org/2000/svg" id="autonaut" width="166" height="140" viewBox="0 0 166 140">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta>
                      
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-stroke {
        fill: #1a2b37!important;
      }

      .cls-1 {
        fill: #1a2b37;
      }

      .cls-1, .cls-10, .cls-11, .cls-12, .cls-13, .cls-14, .cls-2, .cls-3, .cls-4, .cls-5, .cls-6, .cls-7, .cls-8, .cls-9 {
        fill-rule: evenodd;
      }

      .cls-2 {
        fill: #e44646;
      }

      .cls-3 {
        fill: #b83d3d;
      }

      .cls-4 {
        fill: #9cbec2;
      }

      .cls-5 {
        fill: #6a9aa0;
      }

      .cls-6 {
        fill: #d8f0f3;
      }

      .cls-7 {
        fill: #fff;
      }

      .cls-8 {
        fill: #9ed8cb;
      }

      .cls-9 {
        fill: #75b2a4;
      }

      .cls-10 {
        fill: #f7fffd;
      }

      .cls-11 {
        fill: #18202b;
      }

      .cls-12 {
        fill: #579ba3;
      }

      .cls-13 {
        fill: #b04848;
      }

      .cls-14 {
        fill: #ea6c6c;
      }
    </style>
  </defs>
  <path id="stroke" class="cls-stroke" d="M42,130.825s52.143,8.988,93-3.995c0,0,29.286-7.133,31-19.973,0,0-.571-15.408-39-21.971l-6-1S107.571,70.334,109,59.92c0,0,12.429-1.141,14-4.993a27.187,27.187,0,0,0,14-23.968s0.429-15.551-9-21.971v-3S122.429,0,105,0c0,0-23.571.285-28,27.963,0,0-8.286-6.563-16-2,0,0-26.429,17.691-29,34.953,0,0-1.571,5.992,2,9.987L38,74.9s3.714,3.139,7,0l3-3s-3.714,6.42-1,10.985c0,0-3.857,1.855-5,1,0,0-4.857-7.989-20-7.989,0,0-5.857,0-8,2l-3,1S7.571,84.03,9,94.873c0,0-1.714,1.141-4,3l-1,1H2a2.089,2.089,0,0,0-2,2v12.983s0,3,2,3H5a33.355,33.355,0,0,0,4,3l1,13.981s0.286,3,3,3a3.888,3.888,0,0,0,4,3S35.286,142.1,42,130.825Z"/>
  <path id="rocketMid" class="cls-2" d="M58,130.825s-4-7.847-4-21.971v-2h74s-0.286,14.552-4,19.973A229.086,229.086,0,0,1,58,130.825Zm0-48.087s-4,7.9-4,22.109v2.01h74s-0.286-14.644-4-20.1A227.693,227.693,0,0,0,58,82.738Z"/>
  <path id="rocketMidshade" class="cls-3" d="M101,88.875a17.919,17.919,0,0,1,17.445,22.345c4.119-.2,6.555-0.368,6.555-0.368-0.4,11.983-3,13.981-3,13.981-30,7.59-62,3-62,3a52.767,52.767,0,0,1-4-16.977l0.034,0C55.229,103.737,58,94.873,58,94.873c1.4-2.6-2-4.993-2-4.993l1-4.993,6-1c18.5-2.746,40,0,40,0h3c6.6,1.6,4,3.995,4,3.995-3,3-11-1-11-1a18.921,18.921,0,0,0-19,7.989c-3.25,4.244-3,10.985-3,10.985,1.045,2.739.1,5.075-2.344,6.032,2.385,0.053,6.757.085,9.1,0.1A17.967,17.967,0,0,1,101,88.875ZM12,134.819c-0.714-3.852-1-6.99-1-6.99s0.086-3.139,0-6.991l3,1v10.985C14,134.534,12,134.819,12,134.819ZM11,87.882s0.286-3.139,1-6.991c0,0,2,.285,2,2V93.874l-3,1C11.086,91.021,11,87.882,11,87.882ZM2,114.846V100.865s3,0.285,3,2v9.987C5,114.561,2,114.846,2,114.846Z"/>
  <path id="rocketEnds" class="cls-4" d="M128,126.83a71.67,71.67,0,0,0,3-19.973,71.675,71.675,0,0,0-3-19.973c36.833,8.988,36,19.973,36,19.973S164.833,117.842,128,126.83Zm-74,3.995c-23-1.831-30-10.985-30-10.985a18.921,18.921,0,0,0,14-11.984A18.921,18.921,0,0,0,24,95.872s7-9.154,30-10.985a96.771,96.771,0,0,0-3,22.97A96.77,96.77,0,0,0,54,130.825Z"/>
  <path id="rocketEndsshade" class="cls-5" d="M131,123.834a26.27,26.27,0,0,0,3-12.982s11.6-1.4,24-4c0,0,5.133-2,4,1C162,107.856,155.4,118.841,131,123.834Zm-103-3s10.2,7.191,23,6.991a110.7,110.7,0,0,1-2-19.973,41.153,41.153,0,0,1-9,0S38.6,116.244,28,120.838Z"/>
  <path id="rocketEndhighlight" class="cls-6" d="M129,87.882a66.978,66.978,0,0,1,30,11.984s1.167,1.332-1,1c0,0-14-6.991-22-7.989,0,0-3.333-.832-3,2A139.555,139.555,0,0,1,132,117s-2.267,7.361-2,0C130,117,132.867,105.643,129,87.882Z"/>
  <path id="logobg" class="cls-stroke" d="M101,91.875a14.985,14.985,0,1,1-15,14.984A14.992,14.992,0,0,1,101,91.875ZM46,84.886a16.8,16.8,0,0,0,12,3s3.857-.713,2,2c0,0-1.571,6.42,0,9.987L75,78.894l-3-3Z"/>
  <path id="color" class="cls-3" d="M110.737,113.991c-0.27.214-.411,0.09-0.48-0.041l-0.647-3.821a0.79,0.79,0,0,1,.132-0.665l3.137-3c0.3-.168.368,0.124,0.384,0.24A12.388,12.388,0,0,1,110.737,113.991Zm0.118-14.1-3.893-.6a1.078,1.078,0,0,1-.8-0.465l-1.839-3.859a0.241,0.241,0,0,1,.237-0.347,12.3,12.3,0,0,1,6.537,4.824l0,0C111.284,99.773,111.035,99.867,110.855,99.892ZM104.5,118.325h0.01a12.191,12.191,0,0,1-7.005,0l0.021,0s-0.445-.177-0.16-0.46l3.043-1.6a1.006,1.006,0,0,1,.6-0.209,0.736,0.736,0,0,1,.595.209l3.054,1.605C104.946,118.15,104.5,118.325,104.5,118.325Zm-8.659-19.5a1.077,1.077,0,0,1-.794.465l-3.893.6c-0.18-.026-0.43-0.119-0.244-0.451l0,0a12.3,12.3,0,0,1,6.5-4.812,0.251,0.251,0,0,1,.305.275Zm-3.442,11.3-0.662,3.906c-0.077.1-.211,0.167-0.435,0a12.39,12.39,0,0,1-2.555-7.264c0.007-.076.063-0.508,0.448-0.237l3.072,2.934A0.789,0.789,0,0,1,92.4,110.129Z"/>
  <path id="star" class="cls-7" d="M112.474,104.512L108,108.62a0.623,0.623,0,0,0-.105.527l0.684,4.318a4.684,4.684,0,0,1,.21,1.843,1.755,1.755,0,0,1-1.684,1.528,2.839,2.839,0,0,1-1.105-.264l-4.526-2.475a0.79,0.79,0,0,0-.948,0L96,116.572a2.838,2.838,0,0,1-1.105.264,1.756,1.756,0,0,1-1.684-1.528,4.7,4.7,0,0,1,.211-1.843l0.684-4.318A0.624,0.624,0,0,0,94,108.62l-4.474-4.108A2.043,2.043,0,0,1,89,103.143a1.84,1.84,0,0,1,.368-1.106,1.887,1.887,0,0,1,1.211-.632l5.684-.842a0.858,0.858,0,0,0,.632-0.369l2.368-5.056a1.825,1.825,0,0,1,3.474,0l2.368,5.056a0.859,0.859,0,0,0,.632.369l5.684,0.842a1.89,1.89,0,0,1,1.211.632,1.841,1.841,0,0,1,.368,1.106A2.043,2.043,0,0,1,112.474,104.512ZM99.687,103.9a1.623,1.623,0,0,0-1.443-1.595,1.667,1.667,0,0,0-1.8,1.517c0,0.016-.007.034-0.011,0.051a15.745,15.745,0,0,1,1.713,2.156s0.452,0.6-.1.785a5.641,5.641,0,0,1-1.782.353,15.551,15.551,0,0,0,.269,3.109c0.09,1.088,1.714,1.088,1.714,1.088a1.342,1.342,0,0,0,1.443-1.53s-0.09-1.176-.09-2.9C99.6,105.337,99.687,103.9,99.687,103.9Zm6.05,2.682c-0.051-.577-0.046-2.167-0.181-2.76a1.667,1.667,0,0,0-1.8-1.517,1.618,1.618,0,0,0-1.433,1.483,15.853,15.853,0,0,1,1.794,2.241s0.452,0.6-.1.785a5.464,5.464,0,0,1-1.617.341c-0.007,1.645-.089,2.679-0.089,2.679a1.342,1.342,0,0,0,1.443,1.53s1.623,0,1.713-1.088a16.915,16.915,0,0,0,.271-3.426v-0.268Z"/>
  <path id="body" class="cls-7" d="M60,99.866s0.2,12.184,11,9.987c0,0,4.2-.8,3-7.989v-2a13.17,13.17,0,0,1,6-8.988s9.4-6.991,9-12.983c0,0,.6-6.192-5-8.988,0,0-3.6-2.8-13-3,0,0-4.4-.4,0-1,0,0,9.4-1,14,2,0,0-.8-3.4-1-4.993,0,0,.4-2.8,2,1,0,0,6.8,16.977,13,20.972,0,0,6,4.594,10,3.995,0,0,2.6-1.4-1-4.993,0,0-5.8-5.393-7-15.979,0,0-1-8.189,0-13.981,0,0-5.4-7.19-25-6.991,0,0-14.8,8.988-25,23.968,0,0-5,7.19-2,11.984,0,0,3.8,5.593,14,3a19.265,19.265,0,0,0,8-5.992s4.2-3,1,1C72,79.893,59.6,89.081,60,99.866Zm46-42.942s0.75,16.228,13,27.963c0,0-.75,3.5-6-1,0,0-15.25-12.383-14-28.961C99,54.926,104.571,54.213,106,56.924Z"/>
  <path id="thrusters" class="cls-8" d="M16,120.838h4.981a33.607,33.607,0,0,0,18.929,9.122s0.569,1.014-1,2.027c0,0-6.689,8.4-22.914,5.068a3.053,3.053,0,0,1-1-2.027V122.865S15.142,121.128,16,120.838Zm0-25.965h5a33.935,33.935,0,0,1,19-8.988s0.571-1-1-2c0,0-6.714-8.275-23-4.993a2.993,2.993,0,0,0-1,2V92.876S15.143,94.588,16,94.873ZM6,99.866a128.681,128.681,0,0,0,0,14.98s3.5,5.992,14,3.995c0,0,13-3.495,15-10.985,0,0-3.333-9.155-16-10.986C19,96.87,8.833,96.038,6,99.866Z"/>
  <path id="thrustersShade" class="cls-9" d="M16,125.831s-0.2,3.8,1,4.994a11.655,11.655,0,0,0,15-1s-3.25,0-9-4.993l-3-3H17a6.325,6.325,0,0,0-1,3m-8-14.98a119.953,119.953,0,0,0,25-1s-4.2,8.988-17,8.988c0,0-6.2-1-8-3C8,114.846,6.4,113.049,8,109.853Zm8-21.971v3.995s0.4,2.2,4,1c0,0,5-4.394,10-5.992C30,86.884,20.2,85.885,16,87.882Z"/>
  <path id="thrustershighlight" class="cls-10" d="M17,83.888s-0.8-3.8,2-3.995c0,0,9.8-1.4,17,3.995,0,0-11.6-1-16,0C20,83.888,17,85.486,17,83.888ZM8,100.865s-0.6.8,0,3c0,0,13.6-4.394,23,1C31,104.86,19.4,93.275,8,100.865Z"/>
  <path id="helmet" class="cls-7" d="M84,46.937s15.4,17.976,37,7.989l-2-2s-10,.6-15-9.987c0,0-3.6-6.192-4-9.987,0,0,4.2-24.168,26-25.965,0,0-18.25-12.533-36,1,0,0-10.6,8.589-11,21.971C79,29.96,80.4,41.544,84,46.937Z"/>
  <path id="lens" class="cls-11" d="M118,53.928s15.6-2.6,17-21.971c0,0,1.35-15.979-11-22.969a11.159,11.159,0,0,0-10,2S99.2,21.172,100,33.955C100,33.955,102.2,51.531,118,53.928Z"/>
  <path id="highlight" class="cls-7" d="M126,12.983s15.2,15.979,0,34.953c0,0,9.2-18.575-2-30.958,0,0-1.2-1.8,0-3Z"/>
  <path id="Visor" class="cls-12" d="M100,29.96s8.4-19.174,26-19.973v-3S103.4,7.59,100,29.96Z"/>
  <path id="earmuffBack" class="cls-13" d="M90.034,31.957s-0.653-6.591,5.439-6.991c0,0,6.309.8,6.527,6.991,0,0,0,7.39-6.527,6.991C95.473,38.948,90.469,38.349,90.034,31.957Z"/>
  <path id="earmuffFront" class="cls-14" d="M89.028,32.457s-0.544-5.179,4.533-5.493c0,0,5.258.628,5.439,5.493,0,0,0,5.807-5.439,5.493C93.561,37.949,89.391,37.478,89.028,32.457Z"/>
  <path id="backpack" class="cls-7" d="M34.034,65.489S31.679,59.3,38.866,50.008c0,0,13.258-17.313,21.6-21.48,0,0,8.536-4.181,13.038.119,0,0,6.991,5.533,2.739,10.825,0,0-5.672,7.991-10.3,10.706,0,0-16.1,14.843-22.318,23.671A4.884,4.884,0,0,1,37.5,72.261,13.194,13.194,0,0,1,34.034,65.489Z"/>
  <path id="shading" class="cls-4" d="M36,58.921s-2.25,7.49,3,11.984c0,0,3,2,5-1,0,0,17.25-19.724,25-24.967,0,0,4.75-6.242-1-3,0,0-16.25,12.234-22,19.973,0,0-3.45,4.394-6,2a10.2,10.2,0,0,1-2-3.995S37,56.674,36,58.921ZM101,3.995S81,12.733,81,29.96c0,0-1,13.981,10,19.973,0,0,6.25,1.748,3-1A27.354,27.354,0,0,1,86,29.96S83.75,15.23,103,5.992c0,0,2.75-.5,2-2C105,3.995,103.8,2.646,101,3.995ZM73,48.935S51.4,66.311,50,75.9c0,0-2.6,6.192,6,7.989,0,0,6.2,1.4,10-2,0,0-10.333-.333-9-6.991,0,0-1-7.656,12-19.973l6-4.993a20.206,20.206,0,0,1,9-1S80.25,44.773,73,48.935Zm28,19.973a51.755,51.755,0,0,1,0-7.989s1-2,2,1c0,0,1.333,11.318,5,15.979A17.027,17.027,0,0,1,101,68.908ZM88.5,64.433s4.036,15.831,17.84,22.91c0,0,2.182-1.214-.89-3.047,0,0-11.744-8.135-13.9-20.753a2.227,2.227,0,0,0-1.889-2.1S87.635,61.887,88.5,64.433ZM67,87.882s-4.333,6.824-2,12.983c0,0,.5,4.993,5,4.993,0,0,2.667-.5,3,1,0,0-1.167,2.663-5,2a8.291,8.291,0,0,1-6-6.99S60.667,93.042,67,87.882Z"/>
  <path id="rivet" class="cls-1" d="M46,106.844a2,2,0,1,1-2,2A2,2,0,0,1,46,106.844ZM47.25,96a1.427,1.427,0,0,1,1.5,1.748,2.336,2.336,0,0,1-2,2,1.427,1.427,0,0,1-1.5-1.748A2.337,2.337,0,0,1,47.25,96Zm2.8-8.239c0.981-.055,1.486.571,1.127,1.4a3.035,3.035,0,0,1-2.427,1.6c-0.981.055-1.486-.571-1.127-1.4A3.034,3.034,0,0,1,50.05,87.758Zm-3.06,32.831a1.424,1.424,0,0,0,1.492-1.747,2.329,2.329,0,0,0-1.99-2A1.424,1.424,0,0,0,45,118.591,2.329,2.329,0,0,0,46.99,120.588Zm2.785,8.239a0.97,0.97,0,0,0,1.121-1.4,3.021,3.021,0,0,0-2.414-1.6,0.97,0.97,0,0,0-1.121,1.4A3.018,3.018,0,0,0,49.775,128.827Z"/>
  <path id="rivet_copy" data-name="rivet copy" class="cls-1" d="M137.078,104.938a1.985,1.985,0,1,1-1.922,1.984A1.953,1.953,0,0,1,137.078,104.938Zm-1.2-8.842a1.406,1.406,0,0,0-1.443,1.742,2.288,2.288,0,0,0,1.923,1.991,1.4,1.4,0,0,0,1.442-1.742A2.287,2.287,0,0,0,135.876,96.1Zm-2.692-7.215a0.958,0.958,0,0,0-1.083,1.394,2.914,2.914,0,0,0,2.332,1.593,0.959,0.959,0,0,0,1.084-1.394A2.916,2.916,0,0,0,133.184,88.881Zm2.942,27.738a1.4,1.4,0,0,1-1.435-1.742,2.284,2.284,0,0,1,1.913-1.992,1.4,1.4,0,0,1,1.435,1.743A2.283,2.283,0,0,1,136.126,116.619Zm-2.678,7.215a0.958,0.958,0,0,1-1.078-1.394,2.9,2.9,0,0,1,2.321-1.593,0.957,0.957,0,0,1,1.078,1.394A2.9,2.9,0,0,1,133.448,123.834Z"/>
</svg>
</div>

<div class='movingstars'>
  <span>
<svg xmlns="http://www.w3.org/2000/svg" width="280" height="8" viewBox="0 0 280 8">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta> 
                           
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-1 {
        fill: #63f7e4;
        fill-rule: evenodd;
      }
    </style>
  </defs>
  <path id="shooting_star" data-name="shooting star" class="cls-1" d="M276.149,0A3.928,3.928,0,0,1,280,4a3.928,3.928,0,0,1-3.851,4C274.023,8,0,6.209,0,4S274.023,0,276.149,0Z"/>
</svg></span>
  <span><svg xmlns="http://www.w3.org/2000/svg" width="280" height="8" viewBox="0 0 280 8">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta>     
                           
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-1 {
        fill: #63f7e4;
        fill-rule: evenodd;
      }
    </style>
  </defs>
  <path id="shooting_star" data-name="shooting star" class="cls-1" d="M276.149,0A3.928,3.928,0,0,1,280,4a3.928,3.928,0,0,1-3.851,4C274.023,8,0,6.209,0,4S274.023,0,276.149,0Z"/>
</svg></span>
  <span><svg xmlns="http://www.w3.org/2000/svg" width="280" height="8" viewBox="0 0 280 8">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta>
    
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-1 {
        fill: #63f7e4;
        fill-rule: evenodd;
      }
    </style>
  </defs>
  <path id="shooting_star" data-name="shooting star" class="cls-1" d="M276.149,0A3.928,3.928,0,0,1,280,4a3.928,3.928,0,0,1-3.851,4C274.023,8,0,6.209,0,4S274.023,0,276.149,0Z"/>
</svg></span>
  <span><svg xmlns="http://www.w3.org/2000/svg" width="140" height="8" viewBox="0 0 280 8">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta>
    
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-1 {
        fill: #63f7e4;
        fill-rule: evenodd;
      }
    </style>
  </defs>
  <path id="shooting_star" data-name="shooting star" class="cls-1" d="M276.149,0A3.928,3.928,0,0,1,280,4a3.928,3.928,0,0,1-3.851,4C274.023,8,0,6.209,0,4S274.023,0,276.149,0Z"/>
</svg></span>
  <span><svg xmlns="http://www.w3.org/2000/svg" width="280" height="8" viewBox="0 0 280 8">
  <metadata><?xpacket begin="﻿" id="W5M0MpCehiHzreSzNTczkc9d"?>
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.6-c138 79.159824, 2016/09/14-01:09:01        ">
   <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
      <rdf:Description rdf:about=""/>
   </rdf:RDF>
</x:xmpmeta>
                           
<?xpacket end="w"?></metadata>
<defs>
    <style>
      .cls-1 {
        fill: #63f7e4;
        fill-rule: evenodd;
      }
    </style>
  </defs>
  <path id="shooting_star" data-name="shooting star" class="cls-1" d="M276.149,0A3.928,3.928,0,0,1,280,4a3.928,3.928,0,0,1-3.851,4C274.023,8,0,6.209,0,4S274.023,0,276.149,0Z"/>
</svg></span>
</div>

<div class="phrases">
  <svg width="100%" height="100%">
    <defs>
      <!--<style type="text/css">
        @font-face {
          font-family: "Proxima";
          src: url('');
        }
      </style>-->
      <mask id="mask" maskUnits="userSpaceOnUse" maskContentUnits="userSpaceOnUse">
        <linearGradient id="linearGradient" gradientUnits="objectBoundingBox" x2="0" y2="1">
          <stop stop-color="white" stop-opacity="0" offset="0%"/>
          <stop stop-color="white" stop-opacity="1" offset="30%"/>
          <stop stop-color="white" stop-opacity="1" offset="70%"/>
          <stop stop-color="white" stop-opacity="0" offset="100%"/>
        </linearGradient>
        <rect width="100%" height="100%" fill="url(#linearGradient)"/>
      </mask>
    </defs>
    <g width="100%" height="100%" style="mask: url(#mask);">
      <g id="phrases"></g>
    </g>
  </svg>
</div>
</body>
</html>