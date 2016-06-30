let chkUser = function (args, obj) {
  for (let i of obj) {
    if (args === i) {
      return true
    } else {
      return false
    }
  }
}

module.exports = chkUser
