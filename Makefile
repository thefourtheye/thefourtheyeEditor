VERSION = 0.0.1
MESSAGE = v${VERSION} (Beta)

default: build

clean:
	rm -rf bin
	rm -rf build

build: clean
	ant

latest: default
	git tag -f latest
	git push --tags

release: default
	git tag -f "v${VERSION}" -m "${MESSAGE}"
	git push --tags
