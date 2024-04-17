#!/bin/bash
IFS='' 
i=1
nbCoveredInstructions=0
nbMissedInstructions=0
nbTotalInstructions=0
while read -r line; do 
    if [[ $i -eq 1 ]] ; then
        i=($i+1)
    else
        nbInstructionsMissed=$(echo $line | cut -d "," -f 4)
        nbInstructionsCovered=$(echo $line | cut -d "," -f 5)
        nbCoveredInstructions=$((nbCoveredInstructions + nbInstructionsCovered))
        nbMissedInstructions=$((nbInstructionsMissed + nbMissedInstructions))
        echo $nbInstructionsMissed
        echo $nbInstructionsCovered
        echo "--------------------"
        echo $nbCoveredInstructions
        echo $nbMissedInstructions
    fi
done < ./target/site/jacoco/jacoco.csv
nbTotalInstructions=$((nbTotalInstructions + nbCoveredInstructions + nbMissedInstructions))
result=$(bc <<< "scale = 2; ( $nbCoveredInstructions / $nbTotalInstructions ) * 100")
echo $result