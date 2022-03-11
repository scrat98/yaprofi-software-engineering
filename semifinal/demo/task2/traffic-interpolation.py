from scipy.optimize import curve_fit

# Load data
data # data loaded from csv and fitted within one day
firstPartData # 22:00-6:00
secondPartData # 6:00-12:00
thirdPartData # 12:00-22:00

# Find coeffs
def linearRegression(x, a, b):
  y = a*x + b
  return y

def squareRegression(x, a, b, c):
  y = a*x*x + b*x + c
  return y

firstPart = optimize.curve_fit(squareRegression, xdata = firstPartData, ydata = y)[0]
secondPart = optimize.curve_fit(squareRegression, xdata = secondPartData, ydata = y)[0]
thirdPart = optimize.curve_fit(linearRegression, xdata = thirdPartData, ydata = y)[0]
print(firstPart, secondPart, thirdPart)
