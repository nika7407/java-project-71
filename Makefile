
GRADLE_WRAPPER := ./app/gradlew
PROJECT_DIR := app
setup:
	@./app/gradlew wrapper --gradle-version 8.3

clean:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) clean

build:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) build

run:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) run

test:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) test

lint:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) checkstyleMain

report:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) jacocoTestReport

install:
	$(GRADLE_WRAPPER) -p $(PROJECT_DIR) install

build-run: build run

.PHONY: build
