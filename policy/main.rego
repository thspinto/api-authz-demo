package demo.app

default allow = false

default username = "anonymous"

default reason = "Ah ah ah, you didn't say the magic word"

allow = true

username = payload.username {
	[header, payload, signature] := io.jwt.decode(input.token)
}
