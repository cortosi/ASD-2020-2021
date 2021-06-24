CFLAGS= -Wpedantic -Wall -Wconversion
EXECUTABLE= bin/application
UNITY_EXE= bin/unit_test
BUILD_DIR= build/
BIN_DIR= bin/
K=5

all: $(EXECUTABLE) unity

app: $(EXECUTABLE)

unity: build/Merge_bin_ins_sort.o build/Merge_bin_ins_sort_test.o build/Comparators.o build/unity.o $(BIN_DIR)
	$(CC) -D SWAP_DIMENSION=$(K) -o $(UNITY_EXE) build/Merge_bin_ins_sort.o build/Merge_bin_ins_sort_test.o build/Comparators.o build/unity.o

unity_run: unity
	$(UNITY_EXE)

build/%.o: src/%.c $(BUILD_DIR)
	$(CC) $(CFLAGS) -c $< -o $@

bin/application: build/Merge_bin_ins_sort.o build/main.o $(BIN_DIR)
	$(CC) -o bin/application build/Merge_bin_ins_sort.o build/main.o

clean: 
	$(RM) build/*.o
	$(RM) bin/*

run: $(EXECUTABLE)
	$(EXECUTABLE)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)
