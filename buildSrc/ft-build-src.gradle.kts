description = "Exposes gradle buildSrc for IDE support"

plugins {
	`kotlin-dsl`
}

configurations {
	"archives" {
		artifacts.clear()
	}
}