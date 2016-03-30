#!/bin/bash
tree --charset=UTF-8 -I 'Icon*|*.class|build|bin' > source_tree.txt
open source_tree.txt
