# On-Excersice
A repository for excersice solutions

This simple app takes as input the path to a file with the following syntax:

```
3|78
4|7765
3|82
2|78
4|14
3|78
2|78
4|12
```

and outputs the same list in the same order but giving additional information
about the second part. The output of the above would be:

```
3|78[1 of 2]
4|7765[1 of 3]
3|82[2 of 2]
4|14[2 of 3]
3|78[1 of 2]
4|12[3 of 3]
```

So it reports how many times the first part exists, excluding first parts
that exist only with same second part i.e. the number 2, or parts that exist
once.

The result is reported to a file at the same path of the incoming file.

The complexity of the algorithm is O(n)
