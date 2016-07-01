import net from 'net'
import vorpal from 'vorpal'
import fs from 'fs'
import bcrypt from 'bcryptjs'

const chkUser = require ('./chkUser')

const hashPWD =function (args) {
  var salt = bcrypt.genSaltSync(10)
  var hash = bcrypt.hashSync(args, salt)
  return hash
}

const comparePWD = function (clean, jumbled) {
    return bcrypt.compareSync(clean, jumbled)
}
const assessment = vorpal()
const fs = require('fs')

let database = []
let server
let port = 667
let host = 'localhost'
let loggedIn = false;
assessment
  .delimiter('$up3rcal1: ')

assessment
  .command('register <username> <password>')
  .description('Registers user with password')
  .action(function (args, callback) {
    if (chkUser(args.username, database)) {
      assessment.log('Username already exists.  Try again.')
    } else {
      var user = args.username
      var hash = hashPWD (args.password)
      database.push(args.username, hash)
    }
    server = net.createConnection(port, host, () =>
      server.write(`${JSON.stringify({User: {user: user, password: hash}})}\n`))
      server.on('data', (data, dt) =>
        assessment.log(data.toString()))
      server.on('end', () =>
        server.end())
      callback()
      })



assessment
  .command('login <username> <password>')
  .description('Allows registered users to login')
  .action(function (args, callback) {
    let hash = hashPWD(args.password)
    if (!chkUser(args.username, database) && !comparePWD(args.password, hash)) {
      assessment.log('Username/password combination invalid.  Please try again.')
      callback()
    } else {
      assessment.log(`Welcome to the system ${args.username}`)
      let user = args.username
      loggedIn = true
      server = net.createConnection(port, host, () =>
      server.write(`${JSON.stringify({User: {user: user, password: hash}})}\n`))
      server.end()
    }
    callback()
  })

  assessment
    .command('files')
    .description('Gets a list of files from the server')
    .alias('list', 'f')
    .action(function (command, args, callback) {
      if(loggedIn !== true) {
        assessment.write('You are not logged in; you cannot access this command.')
        callback()
      } else {
        server = net.createConnection(port, host, () =>
      server.write(`${command}`))
      server.on('data', (data, dt) =>
        fs.readFileSync(data)
      server.on('end', () =>
        server.end())
      }
      loggedIn = false
      server.end()
      callback()
    })

    assessment
      .command('upload <fileID> [filePath]')
      .description('Allows user to upload file to server')
      .alias('up')
      .action(function (command, args, callback) {
        if(loggedIn !== true) {
          assessment.write('You are not logged in; you cannot access this command.')
          callback()
        } else {
          server = net.createConnection(port, host, () =>
          server.write(`${command}`))
          server.on('data', (data, dt) =>
            assessment.log(data.toString()))
          server.on('end', () =>
            server.end())
          }
          loggedIn = false
          server.end()
          callback()
        })

        assessment
          .command('download <fileID> [filePath]')
          .description('Allows user to upload file to server')
          .alias('dl')
          .action(function (command, args, callback) {
            if(loggedIn !== true) {
              assessment.write('You are not logged in; you cannot access this command.')
              callback()
            } else {
              server = net.createConnection(port, host, () =>
              server.write(`${command}`))
              server.on('data', (data, dt) =>
                assessment.log(data.toString()))
              server.on('end', () =>
                server.end())
            }
            loggedIn = false;
            server.end()
            callback()
          })

export default assessment
