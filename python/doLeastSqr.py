from scipy.sparse import csr_matrix
import scipy.sparse.linalg as sp
import numpy as np

import sys, getopt

def readFloatArray(array_file):
    "returns the 1D array contained iin the file"
    f= open(array_file, "r")
    b= []
    for line in f:
        b.append(float(line))
    f.close()
    return np.array(b)


def readIntArray(array_file):
    "returns the 1D array contained iin the file"
    f= open(array_file, "r")
    b= []
    for line in f:
        b.append(int(line))
    f.close()
    return np.array(b)


def doLeastSquares(argv):
    #parse the commandline input
    file_i = ''
    file_j = ''
    file_val = ''
    file_b = ''
    file_x_out = ''
    try:
        opts, args = getopt.getopt(argv, "i:j:v:b:x:",
                                   ["ifile=","jfile=",
                                   "valfile=", "bfile=",
                                   "xfile="])
    except getopt.GetoptError:
        print('doLeastSqr.py -i <inputfile> -j <inputfile> -v <inputfile> -b <inputfile> -x <outputfile>')
        sys.exit(2)
    
    for opt, arg in opts:
        if opt in ("-b", "--bfile"):
            file_b = arg
        elif opt in ("-i", "--ifile"):
            file_i = arg
        elif opt in ("-j", "--jfile"):
            file_j = arg
        elif opt in ("-x", "--xfile"):
            file_x_out = arg
        elif opt in ("-v", "--valfile"):
            file_val = arg

    #read in the specified files
    b= readFloatArray(file_b)
    rows = readIntArray(file_i)
    cols = readIntArray(file_j)
    vals = readFloatArray(file_val)
    
    #do the work
    ij = [rows,cols]
    mat = csr_matrix((vals,ij))
    result = sp.lsqr(mat,b)

    #write out result
    f = open(file_x_out, 'w')
    for x_i in result[0]:
        f.write('%f\n' %(x_i))
    f.close()
    print('iterations: %i; error |Ax-b|: %f' %(result[2],result[3]))

    
if __name__ == '__main__':
    doLeastSquares(sys.argv[1:])