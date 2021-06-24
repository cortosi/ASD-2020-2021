#ifndef Edit_distance_h
#define Edit_distance_h

#include <stdio.h>

int edit_distance(char *s1, char *s2);

int edit_distance_dyn(char *s1, char *s2);

int edit_distance_recurisive(char *s1, char *s2, int l1, int l2, int dp[l1][l2]);

char *rest(char *s);

int min(int, int, int);

#endif
