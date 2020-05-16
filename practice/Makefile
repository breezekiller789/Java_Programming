CC = g++
CCFLAGS = -Wall -Werror -g -c
SRC = $(wildcard *.java)
OBJ = $(patsubst %.java, %.class, $(SRC))
EXE = hw2
RED = \033[1;31m
GREEN = \033[1;32m
YELLOW = \033[1;33m
BLUE = \033[1;34m
NC = \033[1;0m

.SECONDARY : $(OBJ)
.PHONY : clean

all : $(OBJ)

%.class : %.java
	@echo "$(RED)Compiling $<...$(NC)"
	javac $<
	@echo "$(GREEN)Done!"

# $(EXE) : $(OBJ)
# 	$(CC) -o $@ $^
# 	@echo "$(GREEN)Done!$(NC)"

# %.o : %.cpp
# 	@echo "$(RED)Compiling...$(NC)"
# 	$(CC) -o $@ $< $(CCFLAGS)

clean :
	@echo "$(BLUE)Cleaning...$(NC)"
	rm *.class
	@echo "$(YELLOW)Done!$(NC)"
