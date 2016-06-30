let chkUser = function (args, arr) {
  for (let i of arr) {
    if (args === i) {
      return true
    } else {
      return false
    }
  }
}

module.exports = chkUser
