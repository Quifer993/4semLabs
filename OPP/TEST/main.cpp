#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>

#define PAUSE_ERROR -1
#define WRITE_ERROR -1
#define END 1
#define TRUE 1
#define SOUND_SIGNAL "\a"

static int count_of_signals = 0;
static int program_exit_flag = 0;

void catchsignal(int sig){
    if(sig == SIGQUIT){
        program_exit_flag = TRUE;
        return;
    }
    int write_result = write(STDOUT_FILENO, SOUND_SIGNAL, sizeof(char));
    if (write_result == WRITE_ERROR) perror("PEEP");
    count_of_signals++;
}

int main(){
    void* sigset_result = sigset(SIGINT, catchsignal);//^C
    if (sigset_result == SIG_ERR){
        perror("sigset");
        return 1;
    }

    sigset_result = sigset(SIGQUIT, catchsignal);//^4
    if (sigset_result == SIG_ERR){
        perror("sigset");
        return 1;
    }

    while(program_exit_flag != END){
        pause();
    }

    printf("\n\n%d times\n", count_of_signals);
    return 0;
}
