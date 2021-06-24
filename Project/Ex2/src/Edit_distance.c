#include "Edit_distance.h"
#include <string.h>
#include <stdlib.h>
#include <limits.h>

int edit_distance(char *s1, char *s2)
{
    if (s1 == NULL || s2 == NULL)
        return -1;

    if (strlen(s1) == 0)
        return (int)strlen(s2);

    if (strlen(s2) == 0)
        return (int)strlen(s1);

    int d_ins, d_ncanc, d_noop;

    if (s1[0] == s2[0])
        d_noop = edit_distance(rest(s1), rest(s2));
    else
        d_noop = INT_MAX;

    d_ins = 1 + edit_distance(rest(s1), s2);
    d_ncanc = 1 + edit_distance(s1, rest(s2));

    return min(d_noop, d_ins, d_ncanc);
}

char *rest(char *s)
{
    if (s != NULL)
        return ++s;
    else
        return NULL;
}

int edit_distance_dyn(char *s1, char *s2)
{
    if (s1 == NULL || s2 == NULL)
        return -1;

    int l1 = (int)strlen(s1);
    int l2 = (int)strlen(s2);

    int dp[l1][l2];
    memset(&dp, -1, sizeof(dp));

    return edit_distance_recurisive(s1, s2, l1, l2, dp);
}

int edit_distance_recurisive(char *s1, char *s2, int l1, int l2, int dp[l1][l2])
{

    if (l1 == 0)
        return l2;

    if (l2 == 0)
        return l1;

    if (dp[l1 - 1][l2 - 1] != -1)
        return dp[l1 - 1][l2 - 1];

    if (s1[l1 - 1] == s2[l2 - 1])
        return dp[l1 - 1][l2 - 1] = edit_distance_recurisive(s1, s2, l1 - 1, l2 - 1, dp);

    return dp[l1 - 1][l2 - 1] = 1 + min(edit_distance_recurisive(s1, s2, l1, l2 - 1, dp),
                                        edit_distance_recurisive(s1, s2, l1 - 1, l2, dp),
                                        1 + edit_distance_recurisive(s1, s2, l1 - 1, l2 - 1, dp));
}

int min(int d_noop, int d_canc, int d_ins)
{
    if (d_noop < d_canc)
        if (d_noop < d_ins)
            return d_noop;
        else
            return d_ins;
    else if (d_canc < d_ins)
        return d_canc;
    return d_ins;
}
