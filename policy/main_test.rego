package demo.app

openapi_mock := {"paths": {"/health": {"get": {"security": [{"oauth2": ["public"]}]}}}}

test_public_path {
	result := allow with data.openapi as openapi_mock
		 with input.path as "/health"
		 with input.method as "GET"

	result == true
}
