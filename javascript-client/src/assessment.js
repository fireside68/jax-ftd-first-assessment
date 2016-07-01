import net from 'net'
import vorpal from 'vorpal'
import bcrypt from 'bcryptjs'
import fs from 'fs'
const chkUser = require ('./chkUser')
import { hash, compare } from './hashing'

const assessment = vorpal()
let database = {}
let loginFlag
let server
assessment.delimiter('cieje: ')

const register = assessment.command('register <username> <password>')
register
  .description('Registers a user with this application')
  .alias('reg', 'r')
  .action(function (args, cb) {
       if (chkUser(args.username, database)) {
        this.log('Username has already been registered.')
      } else {
        let hashed = hash(args.password)
	      database[args.username] = hashed
        let username = args.username
        try {
        server = net.createConnection('667', () => {
          server.write(JSON.stringify({ username }))
          server.write(JSON.stringify({ hashed }))
          server.write('\n')
          server.end()
        })
      } catch (err) {
         throw err
      }
    }}
  )

module.exports = assessment
