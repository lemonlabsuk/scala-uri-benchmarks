var ScalaMeter = (function(parent) {
  var my = { name: "data" };
  my.index = [{"scope" : ["Uri-Parsing", "path-length"], "name" : "Test-0", "file" : "..\/Uri-Parsing.path-length.Test-0.dsv"}, {"scope" : ["Uri-Parsing", "domain-length"], "name" : "Test-1", "file" : "..\/Uri-Parsing.domain-length.Test-1.dsv"}, {"scope" : ["Uri-Parsing", "query-string-key-length"], "name" : "Test-2", "file" : "..\/Uri-Parsing.query-string-key-length.Test-2.dsv"}, {"scope" : ["Uri-Parsing", "query-string-value-length"], "name" : "Test-3", "file" : "..\/Uri-Parsing.query-string-value-length.Test-3.dsv"}, {"scope" : ["Uri-Parsing", "number-of-query-string-pairs"], "name" : "Test-4", "file" : "..\/Uri-Parsing.number-of-query-string-pairs.Test-4.dsv"}];
  my.tsvData = ['date	param-String Length	value	success	cilo	cihi	units	complete\n2016-01-18T20:41:18Z	1	0.11549999999999999	true	0.072	0.159	ms	"0.112 0.114 0.136 0.143 0.091 0.113 0.114 0.14 0.124 0.152 0.222 0.226 0.063 0.084 0.111 0.129 0.061 0.061 0.122 0.174 0.063 0.073 0.113 0.145 0.079 0.092 0.096 0.126 0.058 0.094 0.096 0.169 0.089 0.112 0.112 0.149"\n2016-01-18T20:41:18Z	201	0.1874722222222223	true	0.138	0.237	ms	"0.122 0.191 0.202 0.23 0.186 0.189 0.219 0.222 0.163 0.18 0.209 0.226 0.121 0.13 0.189 0.238 0.192 0.198 0.223 0.226 0.128 0.198 0.222 0.229 0.11 0.123 0.128 0.15 0.152 0.206 0.215 0.342 0.158 0.165 0.177 0.19"\n2016-01-18T20:41:18Z	401	0.22255555555555556	true	0.166	0.279	ms	"0.166 0.174 0.178 0.184 0.163 0.167 0.177 0.18 0.222 0.227 0.236 0.238 0.247 0.265 0.266 0.275 0.3 0.318 0.333 0.356 0.16 0.167 0.181 0.194 0.206 0.235 0.248 0.251 0.177 0.203 0.27 0.278 0.169 0.187 0.202 0.212"\n2016-01-18T20:41:18Z	601	0.2263333333333334	true	0.185	0.267	ms	"0.193 0.211 0.222 0.244 0.214 0.216 0.242 0.292 0.192 0.193 0.204 0.214 0.21 0.222 0.252 0.3 0.282 0.298 0.317 0.319 0.203 0.207 0.209 0.219 0.181 0.182 0.184 0.203 0.19 0.205 0.225 0.232 0.209 0.21 0.221 0.231"\n2016-01-18T20:41:18Z	801	0.3324722222222223	true	0.246	0.419	ms	"0.271 0.279 0.301 0.325 0.372 0.399 0.409 0.417 0.246 0.249 0.254 0.263 0.229 0.246 0.248 0.275 0.34 0.343 0.345 0.351 0.344 0.344 0.349 0.359 0.41 0.422 0.467 0.48 0.226 0.242 0.258 0.262 0.286 0.372 0.449 0.537"\n2016-01-18T20:41:18Z	1001	0.3691388888888889	true	0.283	0.455	ms	"0.282 0.288 0.308 0.322 0.413 0.44 0.515 0.525 0.402 0.408 0.417 0.431 0.311 0.313 0.318 0.345 0.304 0.459 0.47 0.531 0.274 0.286 0.361 0.366 0.295 0.3 0.301 0.321 0.28 0.301 0.304 0.31 0.39 0.461 0.461 0.476"\n2016-01-18T20:41:18Z	1201	0.43636111111111103	true	0.294	0.578	ms	"0.518 0.525 0.656 0.663 0.324 0.328 0.337 0.339 0.429 0.471 0.479 0.491 0.492 0.499 0.54 0.552 0.327 0.377 0.572 0.956 0.318 0.32 0.346 0.357 0.319 0.335 0.347 0.35 0.336 0.374 0.402 0.479 0.356 0.387 0.398 0.41"\n2016-01-18T20:41:18Z	1401	0.4544722222222222	true	0.358	0.551	ms	"0.368 0.413 0.418 0.434 0.365 0.37 0.37 0.397 0.586 0.61 0.616 0.643 0.409 0.417 0.476 0.544 0.516 0.566 0.59 0.648 0.36 0.378 0.404 0.423 0.376 0.39 0.396 0.403 0.394 0.396 0.425 0.442 0.386 0.431 0.46 0.541"\n2016-01-18T20:41:18Z	1601	0.4949444444444444	true	0.322	0.667	ms	"0.414 0.415 0.476 0.545 0.13 0.132 0.145 0.156 0.598 0.615 0.637 0.658 0.444 0.543 0.586 0.619 0.46 0.496 0.705 0.735 0.467 0.523 0.544 0.572 0.421 0.427 0.433 0.435 0.402 0.473 0.549 0.603 0.482 0.498 0.7 0.78"\n2016-01-18T20:41:18Z	1801	0.5012777777777778	true	0.229	0.774	ms	"0.497 0.544 0.736 0.829 0.142 0.146 0.166 0.177 0.467 0.467 0.492 0.603 0.242 0.247 0.488 0.511 0.479 0.512 0.579 0.612 0.38 0.445 0.54 0.542 0.186 0.209 0.211 0.254 0.345 0.827 1.022 1.172 0.724 0.729 0.756 0.768"\n', 'date	param-String Length	value	success	cilo	cihi	units	complete\n2016-01-18T20:41:18Z	1	0.06024999999999999	true	0.045	0.076	ms	"0.038 0.048 0.07 0.086 0.046 0.053 0.059 0.064 0.059 0.063 0.073 0.079 0.038 0.045 0.052 0.072 0.062 0.064 0.077 0.079 0.037 0.045 0.058 0.07 0.061 0.066 0.076 0.079 0.039 0.045 0.068 0.084 0.038 0.046 0.058 0.072"\n2016-01-18T20:41:18Z	201	0.18083333333333332	true	0.143	0.218	ms	"0.176 0.178 0.197 0.218 0.123 0.124 0.134 0.15 0.137 0.202 0.217 0.241 0.194 0.199 0.202 0.21 0.138 0.205 0.215 0.219 0.173 0.185 0.217 0.228 0.12 0.134 0.145 0.173 0.139 0.153 0.166 0.17 0.192 0.206 0.211 0.219"\n2016-01-18T20:41:18Z	401	0.27077777777777773	true	0.227	0.314	ms	"0.293 0.305 0.311 0.315 0.208 0.217 0.217 0.219 0.251 0.292 0.306 0.312 0.277 0.279 0.279 0.295 0.231 0.241 0.271 0.328 0.228 0.292 0.306 0.31 0.211 0.216 0.223 0.223 0.259 0.279 0.295 0.33 0.234 0.242 0.325 0.328"\n2016-01-18T20:41:18Z	601	0.3723888888888889	true	0.259	0.486	ms	"0.298 0.31 0.405 0.465 0.291 0.305 0.309 0.322 0.304 0.304 0.324 0.328 0.291 0.301 0.311 0.332 0.349 0.462 0.545 0.607 0.295 0.316 0.406 0.497 0.285 0.302 0.316 0.333 0.431 0.522 0.609 0.67 0.305 0.31 0.322 0.324"\n2016-01-18T20:41:18Z	801	0.4303333333333333	true	0.326	0.534	ms	"0.379 0.389 0.392 0.4 0.385 0.39 0.411 0.412 0.38 0.381 0.396 0.411 0.389 0.392 0.394 0.396 0.573 0.64 0.721 0.787 0.386 0.388 0.395 0.407 0.374 0.381 0.383 0.388 0.386 0.412 0.463 0.503 0.397 0.397 0.405 0.409"\n2016-01-18T20:41:18Z	1001	0.5928611111111111	true	0.417	0.769	ms	"0.465 0.468 0.469 0.491 0.73 0.808 0.823 0.896 0.462 0.466 0.473 0.481 0.504 0.56 0.757 1.073 0.483 0.484 0.51 0.541 0.474 0.475 0.507 0.664 0.458 0.461 0.464 0.527 0.478 0.508 0.565 0.601 0.755 0.787 0.835 0.84"\n2016-01-18T20:41:18Z	1201	0.7978888888888888	true	0.667	0.929	ms	"0.822 0.836 0.877 0.879 0.863 0.984 1.036 1.052 0.827 0.83 0.833 0.883 0.611 0.73 0.741 0.745 0.838 0.85 0.856 0.865 0.795 0.799 0.804 0.811 0.793 0.823 0.831 0.873 0.569 0.57 0.602 0.622 0.569 0.61 0.828 0.867"\n2016-01-18T20:41:18Z	1401	0.908777777777778	true	0.699	1.118	ms	"0.936 0.953 0.975 1.018 0.976 1.003 1.038 1.068 1.018 1.026 1.349 1.446 0.781 0.897 0.931 1.089 0.834 0.975 0.985 1.003 0.635 0.635 0.636 0.637 0.654 0.743 0.853 1.081 0.661 0.672 0.675 0.678 0.921 0.973 0.976 0.985"\n2016-01-18T20:41:18Z	1601	0.9178611111111109	true	0.716	1.119	ms	"0.739 0.74 0.746 0.746 0.732 0.74 0.744 0.744 0.821 1.09 1.119 1.171 1.113 1.176 1.186 1.217 0.903 0.945 1.099 1.337 0.758 0.77 0.831 0.836 0.735 0.746 0.781 0.832 0.772 0.81 1.04 1.168 0.768 0.95 0.962 1.176"\n2016-01-18T20:41:18Z	1801	0.7989166666666663	true	0.488	1.109	ms	"0.391 0.505 0.895 0.901 0.573 0.879 0.885 0.911 0.804 0.821 0.889 0.927 0.882 0.882 0.886 0.933 0.857 0.873 1.198 1.212 0.221 0.246 0.255 0.267 0.429 0.889 0.889 0.967 0.837 1.071 1.194 1.255 0.354 0.764 0.898 1.121"\n', 'date	param-String Length	value	success	cilo	cihi	units	complete\n2016-01-18T20:41:18Z	1	0.15647222222222223	true	0.126	0.187	ms	"0.183 0.188 0.189 0.194 0.179 0.181 0.188 0.19 0.133 0.141 0.153 0.154 0.133 0.137 0.153 0.164 0.095 0.102 0.107 0.123 0.185 0.187 0.195 0.203 0.132 0.141 0.141 0.153 0.131 0.139 0.152 0.179 0.143 0.152 0.153 0.16"\n2016-01-18T20:41:18Z	201	0.26533333333333337	true	0.043	0.488	ms	"0.18 0.186 0.204 0.238 0.182 0.184 0.2 0.21 0.173 0.177 0.191 0.207 0.193 0.205 0.206 0.216 0.19 0.244 0.276 0.302 0.322 0.693 0.974 1.073 0.18 0.183 0.184 0.21 0.17 0.182 0.207 0.208 0.183 0.191 0.207 0.221"\n2016-01-18T20:41:18Z	401	0.4508333333333334	true	-0.005	0.907	ms	"0.258 0.284 0.308 0.332 0.277 0.285 0.296 0.297 0.291 0.308 0.472 0.506 0.267 0.293 0.295 0.312 0.285 0.414 1.334 2.538 0.637 0.754 0.811 0.879 0.266 0.268 0.3 0.318 0.272 0.286 0.295 0.313 0.268 0.302 0.303 0.306"\n2016-01-18T20:41:18Z	601	0.42300000000000004	true	0.316	0.530	ms	"0.343 0.346 0.369 0.382 0.585 0.617 0.676 0.765 0.357 0.36 0.398 0.432 0.337 0.351 0.359 0.363 0.394 0.415 0.446 0.478 0.381 0.403 0.517 0.523 0.343 0.357 0.362 0.397 0.365 0.375 0.404 0.432 0.362 0.384 0.42 0.43"\n2016-01-18T20:41:18Z	801	0.5265277777777777	true	0.439	0.614	ms	"0.446 0.453 0.454 0.457 0.453 0.489 0.519 0.526 0.492 0.51 0.541 0.561 0.426 0.453 0.479 0.628 0.581 0.655 0.66 0.673 0.448 0.474 0.496 0.524 0.462 0.567 0.609 0.71 0.453 0.457 0.495 0.591 0.479 0.484 0.536 0.714"\n2016-01-18T20:41:18Z	1001	0.8526388888888887	true	0.415	1.290	ms	"0.884 0.926 1.08 1.113 0.838 0.852 0.887 0.934 0.819 0.828 0.834 0.874 0.726 0.744 0.747 0.808 0.575 0.598 1.37 2.942 0.528 0.553 0.556 0.61 0.819 0.86 0.883 0.984 0.641 0.863 0.876 0.98 0.522 0.538 0.548 0.555"\n2016-01-18T20:41:18Z	1201	0.8248888888888888	true	0.559	1.091	ms	"1.195 1.208 1.366 1.486 1.018 1.036 1.068 1.096 0.609 0.62 0.632 0.663 0.621 0.632 0.637 0.645 0.953 0.961 0.985 0.992 0.659 0.96 0.971 1.01 0.603 0.626 0.63 0.662 0.611 0.636 0.649 0.652 0.636 0.647 0.656 0.665"\n2016-01-18T20:41:18Z	1401	1.1057777777777777	true	0.076	2.135	ms	"1.142 1.148 1.167 1.234 0.808 1.099 1.149 1.221 0.724 0.753 0.756 0.795 0.71 0.713 0.72 0.73 1.129 1.261 2.594 6.324 0.766 0.784 0.838 0.895 0.785 0.93 0.93 1.16 0.725 0.743 0.786 0.851 0.734 0.772 0.875 1.057"\n2016-01-18T20:41:18Z	1601	0.8871111111111111	true	0.691	1.084	ms	"0.863 0.866 0.883 0.906 0.792 0.839 0.888 0.98 0.337 0.526 0.577 0.601 0.788 0.892 0.895 0.912 0.934 0.963 1.267 1.37 0.855 0.925 1.014 1.075 0.798 0.862 0.965 1.048 0.827 0.911 0.911 0.943 0.844 0.885 0.945 1.049"\n2016-01-18T20:41:18Z	1801	1.2116666666666664	true	0.373	2.051	ms	"0.36 0.634 0.91 0.941 1.225 1.341 1.557 1.708 0.243 0.254 0.28 0.29 1.476 1.924 2.312 2.414 1.327 1.33 1.433 1.448 0.377 1.172 1.557 1.673 0.259 0.265 0.269 0.278 1.194 1.812 2.135 3.475 0.56 1.153 2.007 2.027"\n', 'date	param-String Length	value	success	cilo	cihi	units	complete\n2016-01-18T20:41:18Z	1	0.1566944444444444	true	0.135	0.179	ms	"0.129 0.137 0.151 0.158 0.176 0.183 0.186 0.211 0.133 0.139 0.152 0.178 0.134 0.14 0.154 0.173 0.132 0.142 0.151 0.153 0.134 0.155 0.16 0.17 0.179 0.181 0.182 0.188 0.132 0.144 0.152 0.16 0.132 0.138 0.153 0.169"\n2016-01-18T20:41:18Z	201	0.21791666666666665	true	0.169	0.267	ms	"0.208 0.209 0.226 0.246 0.181 0.186 0.204 0.204 0.173 0.176 0.187 0.189 0.169 0.175 0.186 0.216 0.169 0.204 0.205 0.216 0.208 0.225 0.31 0.373 0.209 0.223 0.24 0.284 0.18 0.181 0.186 0.213 0.249 0.264 0.273 0.298"\n2016-01-18T20:41:18Z	401	0.3456111111111111	true	0.230	0.461	ms	"0.271 0.299 0.3 0.302 0.265 0.279 0.304 0.346 0.257 0.268 0.283 0.292 0.498 0.541 0.64 0.704 0.264 0.284 0.309 0.328 0.267 0.282 0.298 0.329 0.284 0.314 0.385 0.459 0.257 0.276 0.299 0.311 0.398 0.398 0.418 0.433"\n2016-01-18T20:41:18Z	601	0.42449999999999993	true	0.334	0.515	ms	"0.37 0.396 0.491 0.507 0.341 0.341 0.376 0.405 0.343 0.364 0.364 0.44 0.337 0.354 0.373 0.405 0.384 0.396 0.416 0.458 0.362 0.475 0.52 0.74 0.372 0.411 0.478 0.538 0.353 0.355 0.37 0.385 0.498 0.5 0.502 0.562"\n2016-01-18T20:41:18Z	801	0.6947777777777776	true	0.248	1.141	ms	"1.353 1.544 1.717 2.357 0.437 0.449 0.583 0.601 0.62 0.629 0.629 0.649 0.479 0.495 0.499 0.504 0.464 0.514 0.525 0.532 0.457 0.461 0.468 0.5 0.473 0.511 0.66 0.74 0.485 0.498 0.501 0.506 0.568 0.767 0.838 0.999"\n2016-01-18T20:41:18Z	1001	0.737888888888889	true	0.547	0.929	ms	"0.771 0.776 0.804 0.827 0.546 0.564 0.579 0.778 0.526 0.548 0.579 0.627 0.544 0.572 0.583 0.735 0.768 0.773 0.926 0.942 0.79 0.833 0.943 1.076 0.526 0.527 0.574 0.687 0.578 0.788 0.816 0.84 0.712 0.879 0.931 1.296"\n2016-01-18T20:41:18Z	1201	0.7784444444444444	true	0.604	0.953	ms	"0.676 0.736 0.985 1.007 0.646 0.658 0.663 0.681 0.598 0.646 0.653 0.658 0.942 0.952 1.104 1.126 0.92 0.933 0.942 0.965 0.644 0.653 0.682 0.687 0.647 0.664 0.67 0.687 0.901 0.927 0.932 0.939 0.617 0.622 0.622 0.639"\n2016-01-18T20:41:18Z	1401	0.9582777777777777	true	0.742	1.175	ms	"1.057 1.07 1.078 1.197 0.707 0.722 0.74 0.747 1.022 1.14 1.194 1.269 0.891 1.205 1.34 1.342 1.035 1.046 1.082 1.115 0.715 0.745 0.801 0.826 0.751 0.802 0.844 0.865 1.044 1.048 1.071 1.086 0.674 0.699 0.731 0.797"\n2016-01-18T20:41:18Z	1601	0.9324722222222221	true	0.624	1.241	ms	"0.806 0.935 1.171 1.315 0.849 0.866 0.9 0.985 0.784 0.854 1.024 1.262 0.776 0.843 0.851 0.862 1.186 1.214 1.292 1.311 0.83 0.935 1.209 1.213 0.261 0.275 0.358 0.519 0.866 1.074 1.221 1.329 0.565 0.705 1.058 1.065"\n2016-01-18T20:41:18Z	1801	0.7624444444444444	true	0.316	1.209	ms	"0.64 0.75 0.972 1.005 0.67 0.686 0.895 0.953 0.259 0.272 0.329 0.417 0.327 0.462 0.609 0.741 0.938 0.94 1.029 1.108 1.056 1.271 1.543 1.687 0.261 0.262 0.271 0.271 1.037 1.066 1.374 1.523 0.261 0.288 0.511 0.764"\n', 'date	param-Num of Query String Pairs	value	success	cilo	cihi	units	complete\n2016-01-18T20:41:18Z	1	0.1450555555555556	true	0.031	0.259	ms	"0.139 0.145 0.147 0.165 0.089 0.099 0.107 0.12 0.072 0.095 0.118 0.122 0.048 0.049 0.063 0.088 0.235 0.28 0.384 0.55 0.105 0.136 0.195 0.366 0.089 0.093 0.138 0.242 0.096 0.113 0.126 0.135 0.046 0.061 0.067 0.099"\n2016-01-18T20:41:18Z	201	0.5081111111111112	true	0.321	0.695	ms	"0.612 0.624 0.801 0.876 0.238 0.244 0.269 0.296 0.293 0.323 0.404 0.491 0.494 0.496 0.531 0.575 0.47 0.477 0.708 0.762 0.518 0.542 0.592 0.641 0.376 0.401 0.417 0.428 0.39 0.412 0.414 0.427 0.51 0.515 0.854 0.871"\n2016-01-18T20:41:18Z	401	0.7786944444444445	true	0.522	1.035	ms	"0.439 0.719 1.025 1.123 0.387 0.395 0.469 0.58 0.734 0.739 0.74 0.749 0.579 0.692 0.93 1.014 0.976 0.983 1.024 1.039 0.902 0.99 1.065 1.114 0.606 0.608 0.619 0.646 0.532 0.595 0.624 0.671 0.648 0.651 1.132 1.294"\n2016-01-18T20:41:18Z	601	1.0891944444444444	true	0.585	1.593	ms	"0.632 0.65 0.652 0.662 0.657 0.676 0.72 0.775 0.947 1.052 1.091 1.201 1.159 1.2 1.705 1.893 0.84 1.348 1.528 2.507 1.073 1.126 1.415 1.551 0.93 0.934 0.966 0.987 0.674 0.683 0.695 0.727 0.778 0.987 1.423 2.367"\n2016-01-18T20:41:18Z	801	1.3239166666666666	true	0.804	1.844	ms	"0.757 0.793 0.793 0.837 0.726 0.734 0.735 0.793 1.43 1.463 1.603 1.664 0.741 0.743 0.971 1.099 1.319 1.337 1.363 1.383 1.053 1.751 2.255 2.312 1.732 1.82 1.851 2.155 0.745 1.189 1.308 1.571 1.326 1.572 1.709 2.028"\n2016-01-18T20:41:18Z	1001	1.7188333333333334	true	1.141	2.297	ms	"0.905 0.942 0.946 0.968 1.554 1.7 1.832 1.853 1.091 1.421 1.756 1.797 1.592 1.691 1.749 1.781 1.57 1.696 1.735 1.877 2.194 2.539 2.575 3.139 1.35 1.428 1.571 1.608 1.579 1.929 2.583 3.079 1.107 1.505 1.538 1.698"\n2016-01-18T20:41:18Z	1201	1.999944444444444	true	1.388	2.612	ms	"1.869 1.885 1.91 2.027 1.439 1.738 1.749 1.783 1.168 1.843 1.898 1.941 1.946 2.053 2.108 2.204 1.826 1.942 1.983 2.093 2.553 2.57 2.606 2.703 1.147 1.622 1.627 2.312 1.814 1.824 1.858 1.905 1.176 1.371 3.661 3.844"\n2016-01-18T20:41:18Z	1401	2.116416666666667	true	1.706	2.527	ms	"1.448 1.86 1.953 1.99 2.118 2.268 2.431 2.741 1.359 1.394 1.903 2.656 2.103 2.104 2.161 2.18 1.686 2.005 2.041 2.356 1.89 2.611 2.623 3.009 2.162 2.27 2.307 2.325 1.276 1.917 2.02 2.023 2.135 2.277 2.289 2.3"\n2016-01-18T20:41:18Z	1601	2.7668333333333326	true	1.269	4.265	ms	"2.667 3.503 4.202 4.321 2.376 2.537 2.642 2.71 2.178 2.366 2.43 2.502 2.042 2.139 2.148 2.324 1.838 2.334 2.497 2.986 1.449 2.24 2.536 2.562 1.406 1.461 2.705 2.791 3.231 3.241 7.261 8.192 1.458 1.717 2.284 2.33"\n2016-01-18T20:41:18Z	1801	3.5056944444444444	true	2.071	4.940	ms	"1.34 1.735 2.108 2.46 1.354 3.156 3.22 4.924 3.185 3.457 3.525 3.859 3.36 4.308 4.631 4.631 2.203 2.213 2.224 2.25 2.841 4.201 4.656 5.298 2.633 3.953 3.956 4.695 2.577 2.911 3.39 3.563 2.576 5.373 6.704 6.735"\n'];
  parent[my.name] = my;
  return parent;
})(ScalaMeter || {});