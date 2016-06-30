import net from 'net'
import vorpal from 'vorpal'
import bcrypt from 'bcryptjs'
import { hash, compare } from './hashing'

const assessment = vorpal()
let database = {}

assessment.delimiter('cieje: ')

const register = assessment.command('register <username> <password>')
register
  .description('Registers a user with this application')
  .alias('reg')
  .action(function (args, cb) {
    return (
      Promise.resolve(users[args.username] !== undefined) // is user already registered?
        .then(
          (alreadyRegistered) =>
            alreadyRegistered
              ? this.log('Username already registered! Choose another one.')
              : hash(args.password)
                .then((hashedPassword) => users[args.username] = hashedPassword)
                .then(() => this.log('Registration successful!'))
        )
        .catch((err) => this.log(`An error occurred: ${err}`))
    )
  })

export default assessment
