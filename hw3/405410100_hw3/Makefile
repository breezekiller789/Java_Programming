CC = javac
SRC = $(wildcard *.java)
EXE = $(patsubst %.java, %.class, $(SRC))
RED = \033[1;31m
GREEN = \033[1;32m
YELLOW = \033[1;33m
BLUE = \033[1;34m
NC = \033[1;0m

$(EXE) : $(SRC)
	@echo "$(RED)Compiling...$(NC)"
	javac $(SRC)
	@echo "$(BLUE)Done!$(NC)"

clean :
	@echo "$(YELLOW)Cleaning...$(NC)"
	rm $(EXE)
	@echo "$(GREEN)Done!$(NC)"
