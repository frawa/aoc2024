day=$1

echo "Creating Day $1"

cp data/day00.part1.sample.txt "data/day${day}.part1.sample.txt"
cp data/day00.part1.txt "data/day${day}.part1.txt"

sed -e "s/Day00/Day${day}/g" -e "s/Part1(0)/Part1(${day})/g" src/main/scala/aoc2024/Day00.scala > "src/main/scala/aoc2024/Day${day}.scala"
sed -e "s/Day00/Day${day}/g" src/test/scala/aoc2024/Day00Suite.scala > "src/test/scala/aoc2024/Day${day}Suite.scala"