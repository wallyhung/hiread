
function bytesToSize(bytes) {
    var k = 1000;
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes === 0) return '0 Bytes';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(k)), 10);
    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}
function makeXMLHttpRequest(url, data, callback) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200) {
            callback(request.responseText);
        }
    };
    request.open('POST', url);
    request.send(data);
}

var app1 = new Vue({
    el: '#app-1',
    data: {
    	mediaConstraints:{audio:true},
    	mediaRecorder:null,
    	recording:false,
    	audios:[],
    	audioIndex:1
    },
    methods: {
    	upload:function(index){
    		var audio=this.audios[index];
        	var file = new File([audio.blob], audio.name, {
	            type: 'audio/wav'
	        });
	        var fd = new FormData();
	        fd.append("file", file);
	        makeXMLHttpRequest(contextRoot+'test/demo/audio/upload', fd, function(result) {
	        	var json=JSON.parse(result);
	        	audio.uploaded=true;
	        	audio.url=json.entity;
	        });
    	},
        toggleRecording:function(){
	        if (this.recording) {
	            this.mediaRecorder.stop();
	            this.mediaRecorder.stream.stop();
	            this.recording=false;
	        } else {
	        	navigator.mediaDevices.getUserMedia(this.mediaConstraints).then(this.onMediaSuccess).catch(this.onMediaError);
	            this.recording=true;
	        }
	    },
	    onMediaSuccess:function (stream) {
	    	var that=this;
	        this.mediaRecorder = new MediaStreamRecorder(stream);
	        this.mediaRecorder.stream = stream;
	        this.mediaRecorder.audioChannels = 1;
	        this.mediaRecorder.recorderType = StereoAudioRecorder;
	        this.mediaRecorder.mimeType = 'audio/wav';
	        this.mediaRecorder.ondataavailable = function(blob) {
//	        	var file = new File([blob], 'msr-' + (new Date).toISOString().replace(/:|\./g, '-') + '.wav', {
//	                type: 'audio/wav'
//	            });
//
//	            var fd = new FormData();
//	            fd.append("file", file);
//
//	            makeXMLHttpRequest(contextRoot+'test/demo/audio/upload', fd, function() {
//	                var downloadURL = 'https://path-to-your-server/uploads/' + file.name;
//	                console.log('File uploaded to this path:', downloadURL);
//	            });
	            
	        	var audio={};
	        	audio.blob=blob;
	        	audio.objUrl=URL.createObjectURL(blob);
	        	audio.size=bytesToSize(blob.size);
	        	audio.index=that.audioIndex++;
	        	audio.name=audio.index+".wav";
	        	audio.uploaded=false;
	        	audio.url='';
	        	that.audios.push(audio);
	        };
	        var timeInterval = 60*1000;
	        this.mediaRecorder.start(timeInterval);
	    },
	    onMediaError:function(e) {
	        console.error('media error', e);
	    }

    }
})