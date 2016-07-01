let database = {}

let strings = ['uno', 'dos', 'tres', 'quatro', 'cinco', 'seis', 'siete', 'ocho', 'nueve', 'dies']

for(let i = 1; i <= strings.length; i++) {
  database[strings[i]] = i
}

console.log(database)

console.log(('man' in database))
console.log(('dos' in database))
