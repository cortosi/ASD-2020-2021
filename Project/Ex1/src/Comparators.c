#include "Comparators.h"

int _Int_comparator(void *p1, void *p2)
{
    if ((*(int *)p1) < (*(int *)p2))
        return -1;
    else if ((*(int *)p1) > (*(int *)p2))
        return 1;
    else
        return 0;
}

int _Double_comparator(void *p1, void *p2)
{
    if ((*(double *)p1) < (*(double *)p2))
        return -1;
    else if ((*(double *)p1) > (*(double *)p2))
        return 1;
    else
        return 0;
}

int _Char_comparator(void *p1, void *p2)
{
    return strcmp(((char *)p1), ((char *)p2));
}
