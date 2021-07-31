package demo.app

default allow = false

default username = "anonymous"

default reason = "Ah ah ah, you didn't say the magic word"

allow {
	userRoles[_] == requestRoles[_]
}

allow {
	requestRoles[_] == "public"
}

username = user.username

user = payload {
	[header, payload, signature] := io.jwt.decode(input.token)
}

userRoles = roles {
	roles := data.groups[user.groups[_]]
}

requestRoles = roles {
	roles := data.openapi.paths[input.path][lower(input.method)].security[_].oauth2
}
