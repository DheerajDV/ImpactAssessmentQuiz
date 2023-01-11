//import "./jquery.dataTables.min.js"
//
//
////document.getElementById("completed").addEventListener("click",gettableentries)
////document.getElementById("passed").addEventListener("click",getpasstableentries)
////document.getElementById("failed").addEventListener("click",function(){
////document.getElementById("pass").style.display='none'
////document.getElementById("complete").style.display='none'
////document.getElementById("fail").style.display="block"
////document.getElementById("overlay").style.display='block'
////})
////document.getElementById("passed").addEventListener("click",function(){
////document.getElementById("pass").style.display='block'
////document.getElementById("complete").style.display='none'
////document.getElementById("fail").style.display="none"
////document.getElementById("overlay").style.display='block'
////})
//
//document.getElementById("employee_failed").addEventListener("click", emp_failed)
//async function emp_failed()
//{
//var fail_array=[]
//  await fetch("Courses/fail")
//  .then(response=>response.json())
//  .then(data=>{
//
//fail_array=data
//  })
//let fail = new DataTable('#emp_fail-table', {
//dom: 'Bfrtip',
//    // options
//     data: fail_array,
//        columns: [
//            { data: 'course_code' },
//            { data: 'course_name' },
//            { data: 'begin_date' },
//            { data: 'completion_date' }
//        ],  buttons: [
//                                      'copy', 'excel', 'pdf'
//                                  ],
//
//    				lengthMenu : [
//    						[ 5, 10],
//    						[ '10 rows', '25 rows',
//    								'50 rows',
//    								'Show all' ] ],
//    				// 								"columnDefs" : [ {
//    				// 									"width" : "300px",
//    				// 									"targets" : 8
//    				// 								} ],
//
//
//
//    			});
//
//}
//
//
////failed entries
//async function initialize(){
////
////let table = new DataTable('#func_wise_course', {
////    // options
//////    dom : 'Bfrtip',
////    				lengthMenu : [
////    						[ 5, 10],
////    						[ '10 rows', '25 rows',
////    								'50 rows',
////    								'Show all' ] ],
////    				//
////    				    dom: 'Bfrtip',
////                             buttons: [
////                                 'copyHtml5',
////                                 'excelHtml5',
////                                 'csvHtml5',
////                                 'pdfHtml5'
////                             ],
////    				scrollX : true,
////
////    			});
//
//var fail_array=[]
//  await fetch("Courses/fail")
//  .then(response=>response.json())
//  .then(data=>{
//
//fail_array=data
//  })
//
//let fail = new DataTable('#fail-table', {
//dom: 'Bfrtip',
//    // options
//     data: fail_array,
//        columns: [
//            { data: 'course_code' },
//            { data: 'course_name' },
//            { data: 'begin_date' },
//            { data: 'completion_date' }
//        ],  buttons: [
//                                      'copy', 'excel', 'pdf'
//                                  ],
//
//    				lengthMenu : [
//    						[ 5, 10],
//    						[ '10 rows', '25 rows',
//    								'50 rows',
//    								'Show all' ] ],
//    				// 								"columnDefs" : [ {
//    				// 									"width" : "300px",
//    				// 									"targets" : 8
//    				// 								} ],
//
//
//
//    			});
//    			var pass_array=[]
//                  await fetch("Courses/pass")
//                  .then(response=>response.json())
//                  .then(data=>{
//
//                pass_array=data
//                  })
//                let pass = new DataTable('#pass-table', {
//                dom: 'Bfrtip',
//                    // options
//                     data: pass_array,
//                        columns: [
//                            { data: 'course_code' },
//                            { data: 'course_name' },
//                            { data: 'begin_date' },
//                            { data: 'completion_date' }
//                        ],
//                        buttons: [
//                                                              'copy', 'excel', 'pdf'
//                                                          ],
//
//                //    dom : 'Bfrtip',
//                    				lengthMenu : [
//                    						[ 5, 10 ],
//                    						[ '10 rows', '25 rows',
//                    								'50 rows',
//                    								'Show all' ] ],
//                    				// 								"columnDefs" : [ {
//                    				// 									"width" : "300px",
//                    				// 									"targets" : 8
//                    				// 								} ],
//
//
//                    			});
//
//
//                    			//total courses
//                    			var complete_array=[]
//                                  await fetch("Courses")
//                                  .then(response=>response.json())
//                                  .then(data=>{
//
//                                complete_array=data
//                                  })
//
//                                let complete = new DataTable('#complete-table', {
//                                    // options
//                                     data: complete_array,
//                                        columns: [
//                                            { data: 'course_code' },
//                                            { data: 'course_name' },
//                                            { data: 'begin_date' },
//                                            { data: 'completion_date' },
//                                            { data: 'status' }
//                                        ],
//                                    dom: 'Bfrtip',
//                                    				lengthMenu : [
//                                    						[ 5, 10],
//                                    						[ '10 rows', '25 rows',
//                                    								'50 rows',
//                                    								'Show all' ] ],
//                                    				// 								"columnDefs" : [ {
//                                    				// 									"width" : "300px",
//                                    				// 									"targets" : 8
//                                    				// 								} ],
//                                    			buttons: [
//                                                                                      'copy', 'excel', 'pdf'
//                                                                                  ],
//
//
//                                    			});
//
////                                    			document.getElementById("fail").style.display='none'
////                                                document.getElementById("complete").style.display='none'
////                                                document.getElementById("pass").style.display='none'
////
////
//
////  var headers = ["Activity_name", "Activity_Code", "Start_Date","End_Date"];
////      var table = document.getElementById("courses-table");
////       // table.setAttribute('id','table');
////
////
////      var heading = document.createElement("h2");
////       //table.className="table  table-striped overflow"
////       //makes a table element for the page
////        //heading.innerHTML="Courses Failed"
////        heading.className="fs-1 fw-bold"
////      // let tableBody= document.getElementById("courses-table-body");
////      for(var i = 0; i < array.length; i++) {
////                      console.log(i);
////                      let markup = "<tr><td>This is row</td> <td>This is row</td><td>This is row</td><td>This is row</td></tr>";
////                      let tableBody = $("#courses-table-body");
////                      //let tableBody = $("#table tbody");
////                      tableBody.append(markup);
//
//
////          var row = table.insertRow(i);
////
////          row.insertCell(0).innerHTML = array[i].course_code;
////          row.insertCell(1).innerHTML = array[i].course_name;
////          row.insertCell(2).innerHTML = array[i].begin_date;
////          row.insertCell(3).innerHTML = array[i].completion_date;
//
//      }
//
//initialize()
