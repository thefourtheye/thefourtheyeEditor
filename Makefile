VERSION = 0.0.1
MESSAGE = v${VERSION} (Beta)

clean:
	rm -rf bin
	rm -rf build

make: clean
	ant

latest: make
	git tag -f latest
	git push --tags

release: make
	git tag -f "v${VERSION}" -m "${MESSAGE}"
	git push --tags

all: latest
