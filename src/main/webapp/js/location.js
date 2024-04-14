/**
 * 
 */

function getGPS() {
	console.log("시도");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (pos) => { // 성공 시
                let lat = pos.coords.latitude;
                let lnt = pos.coords.longitude;
            	console.log("성공 - " + lat + ", " + lnt);
                document.getElementById("lat").value = lat;
                document.getElementById("lnt").value = lnt;
            }, (err) => { // 실패 시
                alert(err.message);
        });
    }
}


console.log("GPS 모듈 불러옴");