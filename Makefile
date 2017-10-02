
prepare:
	npm install
	node_modules/.bin/react-native init foregather
	rm -fr node_modules
	mv foregather/android .
	mv foregather/ios .
	mv foregather/node_modules .
	rm -fr foregather
	npm install react-native-navigation
	sbt android:dev
	node_modules/.bin/react-native run-android

