# Укажите путь к Gradle wrapper, если нужно
GRADLE_WRAPPER=./gradlew

# Укажите основную задачу для сборки и тестирования
.DEFAULT_GOAL := build

# Задача очистки
clean:
	$(GRADLE_WRAPPER) clean

# Задача для сборки проекта
build:
	$(GRADLE_WRAPPER) build

# Задача для тестирования
test:
	$(GRADLE_WRAPPER) test

# Задача для линтинга кода (если используется Checkstyle)
lint:
	$(GRADLE_WRAPPER) checkstyleMain

# Задача для генерации отчета по покрытию тестами (с использованием JaCoCo)
coverage:
	$(GRADLE_WRAPPER) jacocoTestReport

# Задача для сборки и тестирования
build-test: build test

# Задача для сборки, тестирования и линтинга
build-test-lint: build test lint

# Задача для всего — сборка, тестирование, линтинг и отчет по покрытиям
full-build: build test lint coverage




