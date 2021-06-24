CFLAGS= -Wpedantic -Wall -Wconversion
EXECUTABLE= bin/application
UNITY_EXE= bin/unit_test
BUILD_DIR= build/
BIN_DIR= bin/

all: $(EXECUTABLE) unity

app: $(EXECUTABLE)

unity: build/Edit_distance.o build/Edit_distance_test.o build/unity.o $(BIN_DIR)
	$(CC) -o $(UNITY_EXE) build/Edit_distance.o build/Edit_distance_test.o build/unity.o

unity_run: unity
	$(UNITY_EXE)

build/%.o: src/%.c $(BUILD_DIR)
	$(CC) $(CFLAGS) -c $< -o $@

bin/application: build/Edit_distance.o build/main.o $(BIN_DIR)
	$(CC) -o bin/application build/Edit_distance.o build/main.o

clean: 
	$(RM) build/*.o
	$(RM) bin/*


run: $(EXECUTABLE)
	$(EXECUTABLE)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)