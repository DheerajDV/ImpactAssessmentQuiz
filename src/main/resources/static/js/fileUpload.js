/**
 * 
 */

		var multipleUploadForm = document.querySelector('#regularVisit');
		var multipleFileUploadInput = document
				.querySelector('#multipleFileUploadInput');
		var multipleFileUploadError = document
				.querySelector('#multipleFileUploadError');
		var multipleFileUploadSuccess = document
				.querySelector('#multipleFileUploadSuccess');

		function uploadMultipleFiles(files) {
						var form = $('#regularVisit')[0];
// var formData = new FormData(form);
						var formData = new FormData();
						for (var i = 0, f; f = files[i]; i++){	
							formData.append("files", f);
							alert(f.size);
						if(f=="undefined")
						return;
 							console.log(f);
						}
// console.log(files[0]);
// var header = $("meta[name='_csrf_header']").attr("content");
// var token = $("meta[name='_csrf']").attr("content");
						var xhr = new XMLHttpRequest();
						xhr.open("POST",[[@{/uploadMultipleFiles}]]);
			// xhr.setRequestHeader(header, token);
						xhr.onload = function() {
							console.log(xhr.responseText);
							var response = JSON.parse(xhr.responseText);
							if (xhr.status == 200) {
								multipleFileUploadError.style.display = "none";
								var content = "<p>All Files Uploaded Successfully</p>";
								response
										.forEach(function(element) {
											content += '<p>DownloadUrl : <a href="' + element.fileDownloadUri + '" target="_blank">'
													+ element.fileName + '</a></p>';
										});
								multipleFileUploadSuccess.innerHTML = content;
								multipleFileUploadSuccess.style.display = "block";
							} else {
								multipleFileUploadSuccess.style.display = "none";
								multipleFileUploadError.innerHTML = (response
										.concat(response.message))
										|| "Some Error Occurred";
							}
						}

						xhr.send(formData);
		}
				multipleUploadForm
						.addEventListener(
								'submit',
								function(event) {
									var files = multipleFileUploadInput.files;
									if (files.length === 0) {
										multipleFileUploadError.innerHTML = "Please select at least one file";
										multipleFileUploadError.style.display = "block";
									}
									uploadMultipleFiles(files);
									event.preventDefault();
								}, true);
