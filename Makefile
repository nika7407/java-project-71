# Указываем, что build и run-dist являются командами
.PHONY: run-dist
.PHONY: build
.PHONY: test
.PHONY: checkstyle

# Команда для запуска приложения
run-dist:
	./build/install/app/bin/app

# Команда для сборки проекта
build:
	./gradlew clean build

# Команда для тестов
test:
	./gradlew test

# Команда для проверки стиля кода
checkstyle:
	./gradlew checkstyleMain checkstyleTest

